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
public class Room {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int number;
	
	private String type;
	
	private double price;
	
	private boolean smoker;
	
	private boolean tv;
	
	private boolean airConditioning;
	
	private boolean breakfast;
	
	@ManyToOne
	@JoinColumn(name = "state", foreignKey = @ForeignKey(name = "FK_ROOMSTATE_ROOM"))
	private RoomState state;
	
	@ManyToOne
	@JoinColumn(name = "floorId", foreignKey = @ForeignKey(name="FK_FLOOR_ROOM"))
	private Floor floor;
	
	@OneToMany(mappedBy="room", cascade=CascadeType.ALL)
	private List<Stay> stays;
	
	// Constructors
	public Room()
	{
		super();
		this.stays = new ArrayList<Stay>();
	}
	
	public Room(int number, String type, double price, boolean smoker, boolean tv, boolean airConditioning, boolean breakfast)
	{
		this.stays = new ArrayList<Stay>();
		this.number = number;
		this.type = type;
		this.price = price;
		this.smoker = smoker;
		this.tv = tv;
		this.airConditioning = airConditioning;
		this.breakfast = breakfast;
	}

	// Getters and setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(boolean airConditioning) {
		this.airConditioning = airConditioning;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public int getId() {
		return id;
	}

	public RoomState getState() {
		return state;
	}

	public void setState(RoomState state) {
		this.state = state;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public List<Stay> getStays() {
		return stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}
	
	public void addStay(Stay stay)
	{
		this.stays.add(stay);
	}
	
}
