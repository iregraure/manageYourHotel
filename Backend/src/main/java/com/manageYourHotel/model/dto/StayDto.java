package com.manageYourHotel.model.dto;

public class StayDto 
{
	// Attributes
	private String startDate;
	
	private String endDate;
	
	private int rate;
	
	private String comment;
	
	private int roomNumber;
	
	private String clientDni;
	
	private String buildingName;

	// Getters and setters
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getClientDni() {
		return clientDni;
	}

	public void setClientDni(String clientDni) {
		this.clientDni = clientDni;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
