package com.manageYourHotel.model.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rating {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int rate;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeId", foreignKey = @ForeignKey(name = "FK_EMPLOYEE_RATING"))
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "ClientId", foreignKey = @ForeignKey(name = "FK_CLIENT_RATING"))
	private Client clientRate;
	
	// Constructors
	public Rating()
	{
		super();
	}
	
	public Rating(Employee employee, Client client)
	{
		this.rate = 0;
		this.employee = employee;
		this.clientRate = client;
	}

	// Getters and setters
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
	
}
