package pt.com.sibs.item;

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
@RequestMapping("/item")
@Tag(name = "Itens", description = "Operations to manage items")
public class ItemContoller {
	
	@Autowired
	private ItemService service;

	@GetMapping
	@Operation(summary = "Get a itens list")
	public ResponseEntity<List<ItemDTO>> getItems() {
		return ResponseEntity.ok(service.getItems());
	}
	
	@PostMapping
	@Operation(summary = "Create a new itens")
	public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) throws Exception{
		return ResponseEntity.ok(service.addItem(itemDTO));
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get a item by id")
	public ResponseEntity<ItemDTO> getItem(Long id){
		return ResponseEntity.ok(service.getItemById(id));
	}
	
	@PatchMapping
	@Operation(summary = "Update a exist item")
	public ResponseEntity<ItemDTO> updateItem(ItemDTO itemDTO) throws Exception{
		return ResponseEntity.ok(service.updateItem(itemDTO));
	}
	
	@DeleteMapping
	@Operation(summary = "Delete a exist item")
	public ResponseEntity<Void> deleteItem(Long id) throws Exception {
		service.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
