package pt.com.sibs.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/order")
@Tag(name = "Order", description = "Operations to manage orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping
	@Operation(summary = "Get a orders list")
	public ResponseEntity<List<OrderDTO>> getOrder() {
		return ResponseEntity.ok(service.getOrder());
	}

	@PostMapping
	@Operation(summary = "Create a new order")
	public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) throws Exception {
		return ResponseEntity.ok(service.addOrder(orderDTO));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a especific order")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(service.getOrderById(id));
	}

	@PatchMapping
	@Operation(summary = "Update a especific order")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO) throws Exception {
		return ResponseEntity.ok(service.updateOrder(orderDTO));
	}

	@DeleteMapping
	@Operation(summary = "Delete a especific order")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws Exception {
		service.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
