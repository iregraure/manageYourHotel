package com.manageYourHotel.model.entity;

import javax.persistence.Entity;

@Entity
public class Client extends Person {

	public Client()
	{
		super();
	}
	
	public Client(String name, String surname, String dni, String email, String address, String phone, String birthDate)
	{
		super(name, surname, dni, email, address, phone, birthDate);
	}
	
}
