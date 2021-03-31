package com.manageYourHotel.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Person {

	// Attributes
	@Column(unique = true)
	private String nss;
	
	private String schedule;
	
	private double salary;
	
	private String holidays;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", foreignKey = @ForeignKey(name = "FK_CATEGORY_EMPLOYEE"))
	private Category category;
	
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	private List<Rating> ratings;
	
	// Constructors
	public Employee()
	{
		super();
		this.ratings = new ArrayList<Rating>();
	}
	
	public Employee(String name, String surname, String dni, String email, String address, String phone, String birthDate, String nss, String schedule, double salary, String holidays, Category category)
	{
		super(name, surname, dni, email, address, phone, birthDate);
		this.ratings = new ArrayList<Rating>();
		this.nss = nss;
		this.schedule = schedule;
		this.salary = salary;
		this.holidays = holidays;
		this.category = category;
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
