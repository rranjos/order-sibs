package pt.com.sibs.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Operations to manage users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	@Operation(summary = "Get a users list")
	public ResponseEntity<List<UserDTO>> getUsers() {
		return ResponseEntity.ok(service.getUsers());
	}
	
	@PostMapping
	@Operation(summary = "Create a new user")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok(service.addUser(userDTO));
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get a especific user")
	public ResponseEntity<UserDTO> getUser(@RequestParam Long id){
		return ResponseEntity.ok(service.getUserById(id));
	}
	
	@PatchMapping
	@Operation(summary = "Update a especific user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok(service.updateUser(userDTO));
	}
	
	@DeleteMapping
	@Operation(summary = "Delete a especific user")
	public ResponseEntity<String> deleteUser(Long id){
		service.deleteUser(id);
		return ResponseEntity.ok("");
	}

}
