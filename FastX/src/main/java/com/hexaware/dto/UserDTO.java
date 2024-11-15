package com.hexaware.dto;

public class UserDTO {
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
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int userId, String name, String dob, String gender, String city, String state, String email,
			String password, String number, String roles) {
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

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", city=" + city
				+ ", state=" + state + ", email=" + email + ", password=" + password + ", number=" + number + ", roles="
				+ roles + "]";
	}
	
	
}