package com.hexaware.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class UserDTO {
	private int userId;
	
	@NotNull(message = "Name should not be null")
    @Pattern(regexp = "^[A-Za-z ]{5,}$", message = "Only alphabets should be of length >= 5")
    private String name;

    @NotNull(message = "Date of birth should not be null")
//    @Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[01])$", message = "Date of birth should be in yyyy-MM-dd format")
    @Past(message="Date of birth should be in Past")
    private LocalDate dob;

    @NotNull(message = "Gender should not be null")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender should be one of the following: Male, Female, Other")
    private String gender;

    @NotNull(message = "City should not be null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "City should only contain alphabets and spaces")
    private String city;

    @NotNull(message = "State should not be null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "State should only contain alphabets and spaces")
    private String state;

    @NotNull(message = "Email should not be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password should not be null")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one number, and one special character")
    private String password;

    @NotNull(message = "Phone number should not be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be 10 digits")
    private String number;
    
    @NotNull(message ="Address should not be null")
    private String address;
    
    private String country;
    
    private String pan;
    private String aadhar;

	private String roles;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	




	public UserDTO(int userId,
			@NotNull(message = "Name should not be null") @Pattern(regexp = "^[A-Za-z ]{5,}$", message = "Only alphabets should be of length >= 5") String name,
			@NotNull(message = "Date of birth should not be null") @Past(message = "Date of birth should be in Past") LocalDate dob,
			@NotNull(message = "Gender should not be null") @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender should be one of the following: Male, Female, Other") String gender,
			@NotNull(message = "City should not be null") @Pattern(regexp = "^[A-Za-z ]+$", message = "City should only contain alphabets and spaces") String city,
			@NotNull(message = "State should not be null") @Pattern(regexp = "^[A-Za-z ]+$", message = "State should only contain alphabets and spaces") String state,
			@NotNull(message = "Email should not be null") @Email(message = "Email should be valid") String email,
			@NotNull(message = "Password should not be null") @Size(min = 8, message = "Password should have at least 8 characters") @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one number, and one special character") String password,
			@NotNull(message = "Phone number should not be null") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be 10 digits") String number,
			@NotNull(message = "Address should not be null") String address, String country, String pan, String aadhar,
			String roles) {
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
		this.country = country;
		this.pan = pan;
		this.aadhar = aadhar;
		this.roles = roles;
	}



	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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



	@Override
	public String toString() {
		return String.format(
				"UserDTO [userId=%s, name=%s, dob=%s, gender=%s, city=%s, state=%s, email=%s, password=%s, number=%s, pan=%s, aadhar=%s, roles=%s]",
				userId, name, dob, gender, city, state, email, password, number, pan, aadhar, roles);
	}

	
	
	
}