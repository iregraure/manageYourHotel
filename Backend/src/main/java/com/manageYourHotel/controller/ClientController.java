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
import com.manageYourHotel.exception.StayException;
import com.manageYourHotel.exception.UserException;
import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.dto.StayDto;
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
	
	// Get a client knowing his/her dni
	@GetMapping("/{dni}")
	public ResponseEntity<?> getClient(@PathVariable String dni)
	{
		ResponseEntity<?> response;
		try
		{
			ClientDto dto = clientService.getClient(dni);
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
	
	// Get a client knowing his/her username
	@GetMapping("/user/{username}")
	public ResponseEntity<?> getClientByUsername(@PathVariable String username)
	{
		ResponseEntity<?> response;
		try
		{
			ClientDto dto = clientService.getClientByUsername(username);
			response = ResponseEntity.ok(dto);
		}
		catch(UserException ue)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ue.getMessage());
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
	
	// Get all stays of a client
	@GetMapping("/{dni}/stays")
	public ResponseEntity<?> getAllStays(@PathVariable String dni)
	{
		ResponseEntity<?> response;
		try
		{
			List<StayDto> stays = clientService.getClientStays(dni);
			response = ResponseEntity.ok(stays);
		}
		catch(ClientException ce)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
		}
		catch(StayException se)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(se.getMessage());
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Get one stay knowing the client dni and the startDate
	@GetMapping("/{dni}/stays/{day}/{month}/{year}")
	public ResponseEntity<?> getStay(@PathVariable String dni, @PathVariable String day, @PathVariable String month, @PathVariable String year)
	{
		ResponseEntity<?> response;
		try
		{
			StayDto stay = clientService.getClientStay(dni, day, month, year);
			response = ResponseEntity.ok(stay);
		}
		catch(ClientException ce)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
		}
		catch(StayException se)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(se.getMessage());
		}
		catch (Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Update a client knowing his/her dni
	@PutMapping("/{dni}")
	public ResponseEntity<?> updateClient(@PathVariable String dni, @RequestBody ClientDto sent)
	{
		ResponseEntity<?> response;
		try
		{
			ClientDto dto = clientService.updateClient(dni, sent);
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
