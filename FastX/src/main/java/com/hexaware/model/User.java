package com.hexaware.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String name;
	private String dob;
	private String gender;
	private String city;
	private String state;
	private String email;
	private String password;
	private String number;
	private String roles;
	
	//one user can make many bookings
	@OneToMany(mappedBy="user")
	private List<Booking> booking=new ArrayList<>();
	
	//one user can make many payments
	@OneToMany(mappedBy="user")
	private List<Payment> payment=new ArrayList<>();
	
	//one operator can have many busses
	@OneToMany(mappedBy="user")
	private List<Bus> bus=new ArrayList<>();
	
	public User() {
		
	}

	public User(int userId, String name, String dob, String gender, String city, String state, String email,
			String password, String number, String roles, List<Booking> booking, List<Payment> payment, List<Bus> bus) {
		super();
		this.userId = userId;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.city = city;
		this.state = state;
		this.email = email;
		this.password = password;
		this.number = number;
		this.roles = roles;
		this.booking = booking;
		this.payment = payment;
		this.bus = bus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public List<Bus> getBus() {
		return bus;
	}

	public void setBus(List<Bus> bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return String.format(
				"User [userId=%s, name=%s, dob=%s, gender=%s, city=%s, state=%s, email=%s, password=%s, number=%s, roles=%s, booking=%s, payment=%s, bus=%s]",
				userId, name, dob, gender, city, state, email, password, number, roles, booking, payment, bus);
	}
	
}
