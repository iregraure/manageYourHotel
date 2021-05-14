package com.manageYourHotel.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Floor {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int number;
	
	@ManyToOne
	@JoinColumn(name = "buildingId", foreignKey = @ForeignKey(name="FK_BUILDING_FLOOR"))
	private Building building;
	
	@OneToMany(mappedBy="floor", cascade=CascadeType.ALL)
	private List<Room> rooms;
	
	// Constructors
	public Floor()
	{
		super();
		this.rooms = new ArrayList<Room>();
	}
	
	public Floor(Building building, int number)
	{
		this.rooms = new ArrayList<Room>();
		this.building = building;
		this.number = number;
	}

	// Getters and setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public void addRoom(Room room)
	{
		this.rooms.add(room);
	}
	
}
