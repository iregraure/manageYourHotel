package com.manageYourHotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.model.entity.Person;
import com.manageYourHotel.repo.ClientRepository;
import com.manageYourHotel.repo.PersonRepository;
import com.manageYourHotel.security.model.User;
import com.manageYourHotel.security.model.dto.SecurityDtoConverter;
import com.manageYourHotel.security.repo.UserRepository;

@Service
public class ClientService {

	// Repository
	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PersonRepository personRepo;

	// Services
	@Autowired
	private UpdateService updateService;

	// Converter
	@Autowired
	private SecurityDtoConverter securityConverter;

	@Autowired
	private DtoConverter converter;

	// Get all clients
	public List<ClientDto> getClients() throws ClientException {
		List<Client> clients = (List<Client>) clientRepo.findAll();
		if (clients.size() == 0) {
			throw new ClientException("There are no clients");
		}
		List<ClientDto> dto = new ArrayList<ClientDto>();
		for (Client client : clients) {
			dto.add(converter.fromClientToClientDto(client));
		}
		return dto;
	}

	// Get a client knowing his/her dni
	public ClientDto getClient(String dni) throws ClientException {
		// Get the person with the dni
		Person person = personRepo.findPersonByDni(dni);
		// Check if the person is an employee
		if (!(person instanceof Client)) {
			throw new ClientException("There is no client with that dni");
		}
		return converter.fromClientToClientDto((Client)person);
	}

	// Update a client knowing his/her username
	public ClientDto updateClient(String dni, ClientDto sent) throws ClientException
	{
		// Get the person with the dni
		Person person = personRepo.findPersonByDni(dni);
		if (person == null)
		{
			throw new ClientException("There is no user with that dni");
		}
		// Update the client
		updateService.updateClient((Client)person, converter.fromClientDtoToClient(sent));
		clientRepo.save((Client)person);
		return converter.fromClientToClientDto((Client)person);
	}
}
