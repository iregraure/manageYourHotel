package com.manageYourHotel.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private int rate;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "roomId", foreignKey = @ForeignKey(name="FK_ROOM_STAY"))
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "clientId", foreignKey = @ForeignKey(name="FK_CLIENT_STAY"))
	private Client client;
	
	// Constructors
	public Stay()
	{
		super();
		this.rate = 0;
	}
	
	public Stay(LocalDateTime startDate, LocalDateTime endDate, Room room, Client client)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.rate = 0;
		this.room = room;
		this.client = client;
	}
	
	// Getters and setters
	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
