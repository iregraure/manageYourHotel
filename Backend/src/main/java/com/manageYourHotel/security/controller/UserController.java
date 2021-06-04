package com.manageYourHotel.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.manageYourHotel.exception.UserException;
import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.dto.EmployeeDto;
import com.manageYourHotel.security.model.dto.SecurityDtoConverter;
import com.manageYourHotel.security.model.dto.UserDto;
import com.manageYourHotel.security.service.UserService;
import com.manageYourHotel.service.UpdateService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController 
{
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UpdateService updateService;
	
	@Autowired
	private SecurityDtoConverter converter;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto dto)
	{
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PostMapping("/signUpClient")
	public ResponseEntity<UserDto> signup(@RequestBody ClientDto dto)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(service.newClient(dto));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signUpEmployee")
	public ResponseEntity<UserDto> signUpEmployee(@RequestBody EmployeeDto dto)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(service.newEmployee(dto));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Get a user knowing the username
	@GetMapping("/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username)
	{
		ResponseEntity<?> response;
		try
		{
			UserDto user = service.getUser(username);
			response = ResponseEntity.ok(user);
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error");
		}
		return response;
	}
	
	// Update a user knowing the username
	@PutMapping("/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserDto dto)
	{
		ResponseEntity<?> response;
		try
		{
			UserDto user = service.updateUser(username, dto);
			response = ResponseEntity.ok(user);
		}
		catch(UserException ue)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ue.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}

}