package com.manageYourHotel.security.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.security.model.User;
import com.manageYourHotel.security.model.enums.Role;
import com.manageYourHotel.security.repo.UserRepository;

@Component
public class SecurityDtoConverter {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	// From User to UserDto
	public UserDto fromUserToUserDto(User user)
	{
		UserDto dto = new UserDto();
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRoles(user.getRoles());
		return dto;
	}
	
	// From ClientDto to User 
	public User fromClientDtoToUser(ClientDto dto)
	{
		User user = new User();
		user.setUsername(dto.getDni());
		user.setPassword(passEncoder.encode(dto.getDni()));
		user.setRoles(Set.of(Role.CLIENT));
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		user.setLastPasswordChange(LocalDateTime.now());
		user.setEnabled(true);
		user.setAuthAttempts(0);
		user.setPasswordExpireDate(LocalDateTime.now().plusMonths(3));
		return user;
	}

}
