package com.manageYourHotel.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.manageYourHotel.security.model.User;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String surname;
	
	@Column(unique = true)
	private String dni;
	
	@Column(unique = true)
	private String email;
	
	private String address;
	
	private String phone;
	
	private String birthDate;
	
	@ManyToOne
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_USER_EMPLOYEE"))
	private User user;
	
	// Constructors
	public Person()
	{
		super();
	}
	
	public Person(String name, String surname, String dni, String email, String address, String phone, String birthDate)
	{
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.birthDate = birthDate;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
