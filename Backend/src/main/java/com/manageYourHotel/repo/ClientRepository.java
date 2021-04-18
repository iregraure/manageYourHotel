package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>
{
	public Client findClientById(int id);
	
	public Client findClientByEmail(String email);
	
	public Client findClientByDni(String dni);
}
