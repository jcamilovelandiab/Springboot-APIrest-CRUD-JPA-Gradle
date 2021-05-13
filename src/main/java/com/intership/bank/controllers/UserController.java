package com.intership.bank.controllers;

import com.intership.bank.entities.User;
import com.intership.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable ("id") Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping(value = "nid/{nid}")
	public ResponseEntity<Optional<User>> getUserByNid(@PathVariable ("nid") String nid){
		return ResponseEntity.ok(userService.getUserByNid(nid));
	}
	
	@GetMapping(value = "lock/nid/{nid}")
	public ResponseEntity<Optional<User>> getUserByNidForRead(@PathVariable ("nid") String nid){
		return ResponseEntity.ok(userService.getUserByNidForRead(nid));
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		try {
			User created = userService.createUser(user);
			return ResponseEntity.created(
					new URI("/api/users/"+ created.getId())
			).body(created);
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
		User client = new User();
		client.setId(id);
		userService.deleteUser(client);
		return ResponseEntity.ok().build();
	}

}
