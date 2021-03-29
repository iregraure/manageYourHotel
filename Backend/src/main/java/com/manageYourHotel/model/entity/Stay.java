package com.manageYourHotel.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	private int rate;
	
	private String comment;
	
	// Constructors
	public Stay()
	{
		super();
		this.rate = 0;
	}
	
	public Stay(LocalDateTime startDate, LocalDateTime endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.rate = 0;
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
	
}
