 package com.manageYourHotel.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.exception.StayException;
import com.manageYourHotel.model.dto.ClientDto;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.StayDto;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.model.entity.Person;
import com.manageYourHotel.model.entity.Stay;
import com.manageYourHotel.repo.ClientRepository;
import com.manageYourHotel.repo.PersonRepository;
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

	// Update a client knowing his/her dni
	public ClientDto updateClient(String dni, ClientDto sent) throws ClientException
	{
		// Get the person with the dni
		Person person = personRepo.findPersonByDni(dni);
		if (person == null)
		{
			throw new ClientException("There is no user with that dni");
		}
		// Check if the person is a client
		if(!(person instanceof Client))
		{
			throw new ClientException("There is no client with that dni");
		}
		// Update the client
		updateService.updateClient((Client)person, converter.fromClientDtoToClient(sent));
		clientRepo.save((Client)person);
		return converter.fromClientToClientDto((Client)person);
	}
	
	// Get all the stays of a client knowing his/her dni
	public List<StayDto> getClientStays(String dni) throws ClientException, StayException
	{
		// Get the person with the dni
		Person person = personRepo.findPersonByDni(dni);
		if (person == null)
		{
			throw new ClientException("There is no user with that dni");
		}
		// Get all the stays and convert them to dto
		List<Stay> stays = ((Client)person).getStays();
		if(stays.size() == 0)
		{
			throw new StayException("There are no stays");
		}
		List<StayDto> dtos = new ArrayList<StayDto>();
		for (Stay stay : stays) 
		{
			dtos.add(converter.fromStayToStayDto(stay));
		}
		return dtos;
	}
	
	// Get one stay knowing the client's dni and the startDate
	public StayDto getClientStay(String dni, String day, String month, String year) throws ClientException, StayException
	{
		// Get the person with the dni
		Person person = personRepo.findPersonByDni(dni);
		if (person == null)
		{
			throw new ClientException("There is no user with that dni");
		}
		// Get all the stays and convert them to dto
		List<Stay> stays = ((Client)person).getStays();
		if(stays.size() == 0)
		{
			throw new StayException("There are no stays");
		}
		// Get the startDate from the parameters
		String stringDate = day + "/" + month + "/" + year;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(stringDate, formatter);
		// Search for the stay with the startDate
		Stay stay = null;
		for(Stay s : stays)
		{
			if(s.getStartDate().equals(startDate))
			{
				stay = s;
			}
		}
		if(stay == null)
		{
			throw new StayException("There is no stay with that startDate");
		}
		return converter.fromStayToStayDto(stay);
	}
	
}
