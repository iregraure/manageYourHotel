package com.manageYourHotel.security.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.UserException;
import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.EmployeeDto;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.model.entity.Employee;
import com.manageYourHotel.repo.ClientRepository;
import com.manageYourHotel.repo.EmployeeRepository;
import com.manageYourHotel.security.model.User;
import com.manageYourHotel.security.model.dto.SecurityDtoConverter;
import com.manageYourHotel.security.model.dto.UserDto;
import com.manageYourHotel.security.repo.UserRepository;
import com.manageYourHotel.service.UpdateService;

@Service
public class UserService implements UserDetailsService 
{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private UpdateService updateService;
	
	@Autowired
	private SecurityDtoConverter securityConverter;
	
	@Autowired
	private DtoConverter converter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist"));
	}

	public UserDetails loadUserById(int id) throws AuthenticationException
	{
		return userRepo.findById(id).orElseThrow(() -> new AuthenticationException("User id doesn't exist"));
	}
	
	// Method to create a new client
	public UserDto newClient(ClientDto dto) throws UserException
	{
		// Check if a user with the username already exists
		User user = userRepo.findUserByUsername(dto.getDni());
		if(user != null)
		{
			throw new UserException("Username already exists");
		}
		// Create a user from the clientDto and save it in the database
		User newUser = securityConverter.fromClientDtoToUser(dto);
		userRepo.save(newUser);
		// Create the client and save it in the database
		Client client = converter.fromClientDtoToClient(dto);
		client.setUser(newUser);
		clientRepo.save(client);
		return securityConverter.fromUserToUserDto(newUser);
	}
	
	// Method to create a new employee
	public UserDto newEmployee(EmployeeDto dto) throws UserException
	{
		// Check if a user with the username already exists
		User user = userRepo.findUserByUsername(dto.getDni());
		if(user != null)
		{
			throw new UserException("Username already exists");
		}
		// Create a user from the employeeDto and save it in the database
		User newUser = securityConverter.fromEmployeeDtoToUser(dto);
		userRepo.save(newUser);
		// Create the employee and save it in the database
		Employee employee = converter.fromEmployeeDtoToEmployee(dto);
		employee.setUser(newUser);
		employeeRepo.save(employee);
		return securityConverter.fromUserToUserDto(newUser);
	}
	
	// Method to find a user knowing the username
	public UserDto getUser(String username) throws UserException
	{
		User user = userRepo.findUserByUsername(username);
		if(user == null)
		{
			throw new UserException("There is no user with that username");
		}
		return securityConverter.fromUserToUserDto(user);
	}
	
	// Method to update a user knowing the username
	public UserDto updateUser(String username, UserDto dto) throws UserException
	{
		User original = userRepo.findUserByUsername(username);
		if(original == null)
		{
			throw new UserException("User doesn't exist");
		}
		User sent = securityConverter.fromUserDtoToUser(dto);
		updateService.updateUser(original, sent);
		userRepo.save(original);
		return securityConverter.fromUserToUserDto(original);
	}

}
