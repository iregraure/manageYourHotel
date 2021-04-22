package com.manageYourHotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageYourHotel.exception.EmployeeException;
import com.manageYourHotel.model.dto.EmployeeDto;
import com.manageYourHotel.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	// Services
	@Autowired
	private EmployeeService employeeService;
	
	// Get an employee knowing his/her username
	@GetMapping("/{username}")
	public ResponseEntity<?> getEmployee(@PathVariable String username)
	{
		ResponseEntity<?> response;
		try
		{
			EmployeeDto dto = employeeService.getEmployee(username);
			response = ResponseEntity.ok(dto);
		}
		catch(EmployeeException ee)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ee.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexected error");
		}
		return response;
	}
	
}
