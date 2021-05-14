package com.manageYourHotel.model.dto;

public class FloorDto 
{
	// Attributes
	private int number;
	
	private String buildingName;
	
	private int copyFloor;

	// Getters and setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getCopyFloor() {
		return copyFloor;
	}

	public void setCopyFloor(int copyFloor) {
		this.copyFloor = copyFloor;
	}
	
}
