package com.manageYourHotel.model.dto;

import org.springframework.stereotype.Component;

import com.manageYourHotel.model.entity.Client;

@Component
public class DtoConverter {

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
}
