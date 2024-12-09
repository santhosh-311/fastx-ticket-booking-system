package com.hexaware.dto;


public class AuthenticationReponse {
	private String jwt;
    private String role;
    private String userName;
	public AuthenticationReponse() {
	}
	public AuthenticationReponse(String jwt, String role, String userName) {
		super();
		this.jwt = jwt;
		this.role = role;
		this.userName = userName;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
