package pt.com.sibs.order;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.sibs.email.EmailSend;
import pt.com.sibs.stock.Stock;
import pt.com.sibs.stock.StockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;

	@Autowired
	private StockService stockService;

	@Autowired
	private EmailSend emailSend;

	private ModelMapper modelMapper = new ModelMapper();

	private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Autowired
	public OrderServiceImpl(final OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<OrderDTO> getOrder() {
		return repository.findAll().stream().map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO addOrder(OrderDTO orderDTO) throws Exception {

		Stock stock = getQuantityItems(orderDTO);

		Order entity = modelMapper.map(orderDTO, Order.class);
		entity.setDataCreation(new Date());

		if (stock != null && (stock.getQuantity() >= orderDTO.getQuantity())) {
			entity.setCompleted(true);
			entity.setStock(new Stock(stock.getId()));
		} else {
			entity.setCompleted(false);
		}
		repository.saveAndFlush(entity);

		if (entity.isCompleted()) {
			logger.info("[ORDER :" + entity.getItem().getName() + " COMPLETED!");
			try {
				sendEmail(entity);
			}catch(Exception e) {
				logger.error("Error to send email.");
			}
			stockService.updateItemsStock(orderDTO.getItem().getId(), orderDTO.getQuantity());
		}

		return orderDTO;
	}

	private Stock getQuantityItems(OrderDTO orderDTO) throws Exception {
		Optional<Stock> entity = stockService.findByItemId(orderDTO.getItem().getId());
		if (entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}

	@Override
	public OrderDTO getOrderById(Long id) {
		OrderDTO o = modelMapper.map(repository.getReferenceById(id), OrderDTO.class);
		return o;
	}

	@Override
	public OrderDTO updateOrder(OrderDTO orderDTO) throws Exception {
		try {
			Order entity = modelMapper.map(orderDTO, Order.class);
			repository.save(entity);

		} catch (Exception e) {
			throw new Exception("Order not found!");
		}
		return orderDTO;
	}

	@Override
	public void deleteOrder(Long id) throws Exception {
		try {
			repository.delete(new Order(id));
		} catch (Exception e) {
			throw new Exception("Order not found!");
		}
	}

	@Override
	public void processIncompletedOrders() {
		List<Order> incompletedOrdersList = repository.findAllByCompleted(false);
		incompletedOrdersList.forEach((order) -> {
			Optional<Stock> stock = stockService.findByItemId(order.getItem().getId());
			stock.ifPresent(s -> {
				if (s.getQuantity() >= order.getQuantity()) {
					Order orderPersist = new Order();
					orderPersist.setId(order.getId());
					orderPersist.setCompleted(true);
					orderPersist.setStock(new Stock(s.getId()));
					orderPersist.setItem(order.getItem());
					orderPersist.setQuantity(order.getQuantity());
					orderPersist.setDataCreation(order.getDataCreation());
					orderPersist.setUser(order.getUser());
					repository.save(orderPersist);
					logger.info("[ORDER :" + s.getItem().getName() + " COMPLETED!");
					try {
						sendEmail(order);
					} catch (Exception e) {
						logger.error("Error email send");
					}
					stockService.updateItemsStock(orderPersist.getItem().getId(), orderPersist.getQuantity());
				}
			});
		});

	}

	private void sendEmail(Order order) {
		emailSend.send(order.getUser().getEmail(), "Order Completed", "Your order was accepted with succefull");
	}

}
