package com.manageYourHotel.model.dto;

import org.springframework.stereotype.Component;

import com.manageYourHotel.model.entity.Category;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.model.entity.Employee;
import com.manageYourHotel.repo.CategoryRepository;

@Component
public class DtoConverter {

	// Repository
	private CategoryRepository categoryRepo;
	
	// From ClientDto to Client
	public Client fromClientDtoToClient(ClientDto dto) {
		Client client = new Client(dto.getName(), dto.getSurname(), dto.getDni(), dto.getEmail(), dto.getAddress(),
				dto.getPhone(), dto.getBirthDate());
		return client;
	}
	
	// From Client to ClientDto
	public ClientDto fromClientToClientDto(Client client)
	{
		ClientDto dto = new ClientDto();
		dto.setName(client.getName());
		dto.setSurname(client.getSurname());
		dto.setDni(client.getDni());
		dto.setEmail(client.getEmail());
		dto.setAddress(client.getAddress());
		dto.setPhone(client.getPhone());
		dto.setBirthDate(client.getBirthDate());
		return dto;
	}
	
	// From EmployeeDto to Employee
	public Employee fromEmployeeDtoToEmployee (EmployeeDto dto)
	{
		Category category = categoryRepo.findCategoryByName(dto.getCategory());
		Employee employee = new Employee(dto.getName(), dto.getSurname(), dto.getDni(), dto.getEmail(), dto.getAddress(),
				dto.getPhone(), dto.getBirthDate(), dto.getNss(), dto.getSchedule(), dto.getSalary(), category);
		return employee;
	}
	
	// From Employee to EmployeeDto
	public EmployeeDto fromEmployeeToEmployeeDto(Employee employee)
	{
		EmployeeDto dto = new EmployeeDto();
		dto.setName(employee.getName());
		dto.setSurname(employee.getSurname());
		dto.setDni(employee.getDni());
		dto.setEmail(employee.getEmail());
		dto.setAddress(employee.getAddress());
		dto.setPhone(employee.getPhone());
		dto.setBirthDate(employee.getBirthDate());
		dto.setNss(employee.getNss());
		dto.setSchedule(employee.getSchedule());
		dto.setSalary(employee.getSalary());
		dto.setCategory(employee.getCategory().getName());
		return dto;
	}
}
