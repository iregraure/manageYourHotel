package com.manageYourHotel.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.exception.EmployeeException;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.model.entity.Employee;
import com.manageYourHotel.model.entity.Room;
import com.manageYourHotel.repo.ClientRepository;
import com.manageYourHotel.security.model.User;

@Service
public class UpdateService {

	// Repository
	@Autowired
	public ClientRepository clientRepo;
	
	// Password encoder
	@Autowired
	private PasswordEncoder passEncoder;

	// Update a client
	public void updateClient(Client original, Client sent) throws ClientException {
		// If the DNI is different, check that it is unique. If not, throw ClientException
		if (!original.getDni().equalsIgnoreCase(sent.getDni())) {
			if (clientRepo.findClientByDni(sent.getDni()) != null) {
				throw new ClientException("DNI already exists");
			}
			original.setDni(sent.getDni());
		}
		// If the email is different, check that it is unique. If not, throw ClientException
		if (!original.getEmail().equalsIgnoreCase(sent.getEmail())) {
			if (clientRepo.findClientByEmail(sent.getEmail()) != null) {
				throw new ClientException("Email already exists");
			}
			original.setEmail(sent.getEmail());
		}
		original.setName((sent.getName() == null) ? original.getName() : sent.getName());
		original.setSurname((sent.getSurname() == null) ? original.getSurname() : sent.getSurname());
		original.setAddress((sent.getAddress() == null) ? original.getAddress() : sent.getAddress());
		original.setPhone((sent.getPhone() == null) ? original.getPhone() : sent.getPhone());
		original.setBirthDate((sent.getBirthDate() == null) ? original.getBirthDate() : sent.getBirthDate());
	}
	
	// Update a room
	public void UpdateRoom(Room original, Room sent)
	{
		original.setType((sent.getType() == null) ? original.getType() : sent.getType());
		original.setPrice((sent.getPrice() == 0) ? original.getPrice() : sent.getPrice());
		original.setSmoker(sent.isSmoker());
		original.setTv(sent.isTv());
		original.setAirConditioning(sent.isAirConditioning());
		original.setBreakfast(sent.isBreakfast());
	}
	
	// Update a user
	public void updateUser(User original, User sent)
	{
		if(sent.getPassword() != null && !(sent.getPassword().equals("")) && !(sent.getPassword().equals(original.getPassword())))
		{
			original.setLastPasswordChange(LocalDateTime.now());
			original.setPasswordExpireDate(LocalDateTime.now().plusMonths(3));
			original.setPassword(passEncoder.encode(sent.getPassword()));
		}
	}
	
	// Update an employee
		public void updateEmployee(Employee original, Employee sent) throws EmployeeException {
			// If the DNI is different, check that it is unique. If not, throw ClientException
			if (!original.getDni().equalsIgnoreCase(sent.getDni())) {
				if (clientRepo.findClientByDni(sent.getDni()) != null) {
					throw new EmployeeException("DNI already exists");
				}
				original.setDni(sent.getDni());
			}
			// If the email is different, check that it is unique. If not, throw ClientException
			if (!original.getEmail().equalsIgnoreCase(sent.getEmail())) {
				if (clientRepo.findClientByEmail(sent.getEmail()) != null) {
					throw new EmployeeException("Email already exists");
				}
				original.setEmail(sent.getEmail());
			}
			original.setName((sent.getName() == null) ? original.getName() : sent.getName());
			original.setSurname((sent.getSurname() == null) ? original.getSurname() : sent.getSurname());
			original.setAddress((sent.getAddress() == null) ? original.getAddress() : sent.getAddress());
			original.setPhone((sent.getPhone() == null) ? original.getPhone() : sent.getPhone());
			original.setBirthDate((sent.getBirthDate() == null) ? original.getBirthDate() : sent.getBirthDate());
			original.setNss((sent.getNss() == null) ? original.getNss() : sent.getNss());
			original.setSchedule((sent.getSchedule() == null) ? original.getSchedule() : sent.getSchedule());
			original.setSalary((sent.getSalary() == 0) ? original.getSalary() : sent.getSalary());
			original.setHolidays((sent.getHolidays() == null) ? original.getHolidays() : sent.getHolidays());
		}

}
