package com.manageYourHotel.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends Person{

	// Attributes
	@Column(unique = true)
	private String nss;
	
	private String schedule;
	
	private double salary;
	
	private String holidays;
	
	// Constructors
	public Employee()
	{
		super();
	}
	
	public Employee(String name, String surname, String dni, String email, String address, String phone, String birthDate)
	{
		super(name, surname, dni, email, address, phone, birthDate);
	}
	
	public Employee(String name, String surname, String dni, String email, String address, String phone, String birthDate, String nss, String schedule, double salary, String holidays)
	{
		super(name, surname, dni, email, address, phone, birthDate);
		this.nss = nss;
		this.schedule = schedule;
		this.salary = salary;
		this.holidays = holidays;
	}

	// Getters and setters
	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getHolidays() {
		return holidays;
	}

	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	
}
