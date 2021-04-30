package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>
{
	// Get a person knowing his userId
	public Person findPersonByUserId(int userId);
	
	public Person findPersonByDni(String dni);
}
