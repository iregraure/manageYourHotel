package com.manageYourHotel.security.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.repo.ClientRepository;
import com.manageYourHotel.security.model.User;
import com.manageYourHotel.security.model.dto.SecurityDtoConverter;
import com.manageYourHotel.security.model.dto.UserDto;
import com.manageYourHotel.security.repo.UserRepository;

@Service
public class UserService implements UserDetailsService 
{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
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
	public UserDto newClient(ClientDto dto)
	{
		// Create a user from the clientDto and save it in the database
		User newUser = securityConverter.fromClientDtoToUser(dto);
		userRepo.save(newUser);
		// Create the client and save it in the database
		Client client = converter.fromClientDtoToClient(dto);
		client.setUser(newUser);
		clientRepo.save(client);
		return securityConverter.fromUserToUserDto(newUser);
	}

}
