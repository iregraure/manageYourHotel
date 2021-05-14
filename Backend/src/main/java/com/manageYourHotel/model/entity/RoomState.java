package com.manageYourHotel.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomState 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String state;
	
	// Constructors
	public RoomState()
	{
		super();
	}
	
	public RoomState(String state)
	{
		this.state = state;
	}

	// Getters and setters
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
