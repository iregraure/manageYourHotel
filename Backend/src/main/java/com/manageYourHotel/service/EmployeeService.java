package com.manageYourHotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.exception.EmployeeException;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.EmployeeDto;
import com.manageYourHotel.model.entity.Client;
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
	
	// Services
	@Autowired
	private UpdateService updateService;
	
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
	
	// Get all the employees
	public List<EmployeeDto> getEmployees() throws EmployeeException
	{
		// Get all the employees
		List<Employee> employees = (List<Employee>) employeeRepo.findAll();
		if(employees.size() == 0)
		{
			throw new EmployeeException("There are no employees");
		}
		// Convert employees to employeeDtos
		List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
		for(Employee employee : employees)
		{
			dtos.add(converter.fromEmployeeToEmployeeDto(employee));
		}
		return dtos;
	}
	
	// Update an employee knowing his/her dni
	public EmployeeDto updateEmployee(String dni, EmployeeDto sent) throws EmployeeException
	{
		// Get the person with the dni
		Person person = personRepo.findPersonByDni(dni);
		if (person == null)
		{
			throw new EmployeeException("There is no user with that dni");
		}
		// Check if the person is a client
		if(!(person instanceof Employee))
		{
			throw new EmployeeException("There is no employee with that dni");
		}
		// Update the employee
		updateService.updateEmployee((Employee)person, converter.fromEmployeeDtoToEmployee(sent));
		employeeRepo.save((Employee)person);
		return converter.fromEmployeeToEmployeeDto((Employee)person);
	}

}
