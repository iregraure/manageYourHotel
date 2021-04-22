package com.manageYourHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.EmployeeException;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.EmployeeDto;
import com.manageYourHotel.model.entity.Employee;
import com.manageYourHotel.model.entity.Person;
import com.manageYourHotel.repo.EmployeeRepository;
import com.manageYourHotel.repo.PersonRepository;
import com.manageYourHotel.security.model.User;
import com.manageYourHotel.security.repo.UserRepository;

@Service
public class EmployeeService 
{
	
	// Repository
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	// Converter
	@Autowired
	private DtoConverter converter;
	
	// Get an employee knowing his/her username
	public EmployeeDto getEmployee(String username) throws EmployeeException
	{
		// Get the user with the username
		User user = userRepo.findUserByUsername(username);
		if (user == null)
		{
			throw new EmployeeException("There is no user with that username");
		}
		// Get the person with the id
		Person person = personRepo.findPersonByUserId(user.getId());
		// Check if the person is an employee
		if(!(person instanceof Employee))
		{
			throw new EmployeeException("There is no employee with that username");
		}
		return converter.fromEmployeeToEmployeeDto((Employee)person);
	}

}
