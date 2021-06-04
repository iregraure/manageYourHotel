package com.manageYourHotel.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends Person {
	
	// Atributes
	@OneToMany(mappedBy="client")
	private List<Stay> stays;
	
	@OneToMany(mappedBy="clientRate", cascade=CascadeType.ALL)
	private List<Rating> ratings;
	
	// Constructors
	public Client()
	{
		super();
		this.stays = new ArrayList<Stay>();
		this.ratings = new ArrayList<Rating>();
	}
	
	public Client(String name, String surname, String dni, String email, String address, String phone, String birthDate)
	{
		super(name, surname, dni, email, address, phone, birthDate);
		this.stays = new ArrayList<Stay>();
		this.ratings = new ArrayList<Rating>();
	}

	public List<Stay> getStays() {
		return stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
}
