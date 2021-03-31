package com.manageYourHotel.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Building {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String name;
	
	@Column(unique = true)
	private String address;
	
	@OneToMany(mappedBy="building", cascade=CascadeType.ALL)
	private List<Floor> floors;
	
	// Constructors
	public Building()
	{
		super();
		this.floors = new ArrayList<Floor>();
	}
	
	public Building(String name, String address)
	{
		this.floors = new ArrayList<Floor>();
		this.name = name;
		this.address = address;
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}
	
}
