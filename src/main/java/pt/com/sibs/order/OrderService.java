package pt.com.sibs.order;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

	List<OrderDTO> getOrder();

	OrderDTO addOrder(OrderDTO orderDTO) throws Exception;

	OrderDTO getOrderById(Long id) throws Exception;;

	OrderDTO updateOrder(OrderDTO orderDTO) throws Exception;

	void deleteOrder(Long id) throws Exception;;

	void processIncompletedOrders();

}
