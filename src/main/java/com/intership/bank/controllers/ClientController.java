package com.intership.bank.controllers;

import com.intership.bank.entities.Client;
import com.intership.bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<List<Client>> getClients() {
		return ResponseEntity.ok(clientService.getAllClients());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Optional<Client>> getClientById(@PathVariable ("id") Long id){
		return ResponseEntity.ok(clientService.getClientById(id));
	}

	@PostMapping
	public ResponseEntity<Client> saveUser(@RequestBody Client client){
		Client createdClient = clientService.createClient(client);
		try {
			return ResponseEntity.created(
					new URI("/api/users/"+ createdClient.getId())
			).body(createdClient);
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
		Client client = new Client();
		client.setId(id);
		System.out.println("Hey there");
		clientService.deleteClient(client);
		return ResponseEntity.ok().build();
	}

}
