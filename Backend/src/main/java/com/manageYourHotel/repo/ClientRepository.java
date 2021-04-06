package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;

import com.manageYourHotel.model.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>
{

}
