package com.manageYourHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.repo.ClientRepository;

@Service
public class UpdateService {

	// Repository
	@Autowired
	public ClientRepository clientRepo;

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

}
