package com.manageYourHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.service.ClientService;

@CrossOrigin
@RestController
@RequestMapping("/client")
public class ClientController {

	// Services
	@Autowired
	private ClientService clientService;
	
	// Get all clients
	@GetMapping("/clients")
	public ResponseEntity<?> getAllClients()
	{
		ResponseEntity<?> response;
		try
		{
			List<ClientDto> clients = clientService.getClients();
			response = ResponseEntity.ok(clients);
		}
		catch (ClientException ce)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Get a client knowing his/her username
	@GetMapping("/{username}")
	public ResponseEntity<?> getClient(@PathVariable String username)
	{
		ResponseEntity<?> response;
		try
		{
			ClientDto dto = clientService.getClient(username);
			response = ResponseEntity.ok(dto);
		}
		catch(ClientException ce)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Update a client knowing his/her username
	@PutMapping("/{username}")
	public ResponseEntity<?> updateClient(@PathVariable String username, @RequestBody ClientDto sent)
	{
		ResponseEntity<?> response;
		try
		{
			ClientDto dto = clientService.updateClient(username, sent);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
		}
		catch(ClientException ce)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
}
