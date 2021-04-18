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

	// Get a client knowing his/her username
	public ClientDto getClient(String username) throws ClientException {
		// Get the user with the username
		User user = userRepo.findUserByUsername(username);
		if (user == null) {
			throw new ClientException("There is no user with that username");
		}
		// Get the person with the user
		Person person = personRepo.findPersonByUserId(user.getId());
		// Get the client with the person's id
		Client client = clientRepo.findClientById(person.getId());
		if (client == null) {
			throw new ClientException("There is no user with that username");
		}
		return converter.fromClientToClientDto(client);
	}

	// Update a client knowing his/her username
	public ClientDto updateClient(String username, ClientDto sent) throws ClientException
	{
		// Get the user with the username
		User user = userRepo.findUserByUsername(username);
		if (user == null)
		{
			throw new ClientException("There is no user with that username");
		}
		// Get the person with the user
		Person person = personRepo.findPersonByUserId(user.getId());
		if (person == null)
		{
			throw new ClientException("There is no user with that username");
		}
		// Update the client
		updateService.updateClient((Client)person, converter.fromClientDtoToClient(sent));
		clientRepo.save((Client)person);
		return converter.fromClientToClientDto((Client)person);
	}
}
