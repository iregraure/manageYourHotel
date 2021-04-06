package com.manageYourHotel.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.security.model.dto.UserDto;
import com.manageYourHotel.security.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController 
{
	
	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto dto)
	{
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@PostMapping("/signUp")
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

}
