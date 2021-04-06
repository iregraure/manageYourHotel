package com.manageYourHotel.security.model.dto;

import java.util.Set;

import com.manageYourHotel.model.enums.Role;

public class UserDto {
	
	// Attributes
	private String username;
	
	private String password;
	
	private Set<Role> roles;

	// Getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
