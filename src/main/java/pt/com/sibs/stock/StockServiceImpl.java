package pt.com.sibs.stock;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.sibs.order.OrderService;

@Service
public class StockServiceImpl implements StockService {

	private final StockRepository repository;

	@Autowired
	private OrderService orderService;

	private ModelMapper modelMapper = new ModelMapper();
	
	private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);

	@Autowired
	public StockServiceImpl(StockRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<StockDTO> getStock() {
		return repository.findAll().stream().map(stock -> modelMapper.map(stock, StockDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public StockDTO addStock(StockDTO stockDTO) throws Exception {
		Optional<Stock> result = findByItemId(stockDTO.getItem().getId());
		Stock entity = modelMapper.map(stockDTO, Stock.class);
		result.ifPresent((stock) ->{
			entity.setQuantity(stock.getQuantity()+stockDTO.getQuantity());
			entity.setId(stock.getId());
		});
		entity.setDataCreation(new Date());
		repository.save(entity);
		logger.info("[STOCK :" + entity.getItem().getName() + " UPDATED!");
		verifyOrders(stockDTO);
		return stockDTO;
	}

	private void verifyOrders(StockDTO stockDTO) {
		orderService.processIncompletedOrders();
	}

	@Override
	public StockDTO getStockById(Long id) {
		return modelMapper.map(repository.getReferenceById(id), StockDTO.class);
	}

	@Override
	public StockDTO updateStock(StockDTO stockDTO) {
		Stock entity = modelMapper.map(stockDTO, Stock.class);
		verifyOrders(stockDTO);
		repository.save(entity);
		return stockDTO;
	}

	@Override
	public void deleteStock(Long id) {
		repository.delete(new Stock(id));

	}

	@Override
	public void updateItemsStock(Long id, Integer quantity) {
		Optional<Stock> result = repository.findByItemId(id);
		if(result.isPresent()) {
			Stock entity = result.get();
			Integer v = entity.getQuantity() - quantity;
			entity.setQuantity(v);
			repository.save(entity);
		}
		
	}

	@Override
	public Optional<Stock> findByItemId(Long id){
		return repository.findByItemId(id);
	}

}
