package pt.com.sibs.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/stock")
@Tag(name = "Stock", description = "Operations to manage stock")
public class StockController {
	
	@Autowired
	private StockService service;

	@GetMapping
	@Operation(summary = "Get a stock list")
	public ResponseEntity<List<StockDTO>> getStock() {
		return ResponseEntity.ok(service.getStock());
	}
	
	@PostMapping
	@Operation(summary = "Create a new Stock")
	public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO)  throws Exception{
		return ResponseEntity.ok(service.addStock(stockDTO));
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get a especific stock")
	public ResponseEntity<StockDTO> getStock(Long id) throws Exception{
		return ResponseEntity.ok(service.getStockById(id));
	}
	
	@PatchMapping
	@Operation(summary = "Update a especific stock")
	public ResponseEntity<StockDTO> updateStock(StockDTO stockDTO)  throws Exception{
		return ResponseEntity.ok(service.updateStock(stockDTO));
	}
	
	@DeleteMapping
	@Operation(summary = "Delete a especific stock")
	public ResponseEntity<Void> deleteStock(Long id) throws Exception{
		service.deleteStock(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
