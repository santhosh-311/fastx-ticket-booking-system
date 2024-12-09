package com.hexaware.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class Users {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String name;
	private LocalDate dob;
	private String gender;
	private String city;
	private String state;
	private String email;
	private String password;
	private String number;
	private String address;
	private String pan;
	private String aadhar;
	private String country;
	

	public Users(int userId, String name, LocalDate dob, String gender, String city, String state, String email,
			String password, String number, String address, String pan, String aadhar, String country, String roles,
			List<Booking> booking, List<Payment> payment, List<Bus> bus) {
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
		this.address = address;
		this.pan = pan;
		this.aadhar = aadhar;
		this.country = country;
		this.roles = roles;
		this.booking = booking;
		this.payment = payment;
		this.bus = bus;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

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
	
	public Users() {
		
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
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
				"Users [userId=%s, name=%s, dob=%s, gender=%s, city=%s, state=%s, email=%s, password=%s, number=%s, pan=%s, aadhar=%s, roles=%s, booking=%s, payment=%s, bus=%s]",
				userId, name, dob, gender, city, state, email, password, number, pan, aadhar, roles, booking, payment,
				bus);
	}

}