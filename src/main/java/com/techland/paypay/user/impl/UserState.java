package com.techland.paypay.user.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techland.paypay.user.helper.Status;

public class UserState {
	
	/**
	 * 
	 */
	
	private String id;
	private  String userType;
	private  String username;
	private  String password;
	private  String email;
	private  String fullname;
	private  String role;
	private  String status;
	
	
	
		
	public UserState() {
		
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public  boolean addEvent(String event) 
	{ 
		boolean success = false;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(event);
  
 
			if (actualObj.get("class").asText().equals("UserAddedEvent"))
			{
			this.setEmail(actualObj.get("email").asText());
			this.setFullname(actualObj.get("fullname").asText());
			this.setId(actualObj.get("id").asText());
			this.setPassword(actualObj.get("password").asText());
			this.setRole(actualObj.get("role").asText());
			this.setUsername(actualObj.get("username").asText());
			this.setUserType(actualObj.get("usertype").asText());
			this.setStatus(Status.EMAILNOTVERIFIED.getName());
			}
			
			if (actualObj.get("class").asText().equals("UserStatusChangedEvent"))
			{
				this.setStatus(actualObj.get("status").asText());
			}
		success = true;
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return success;
	}



	@Override
	public String toString() {
		return "{\"class\":\"UserState\",\"id\":\"" + id + "\", \"userType\":\"" + userType + "\", \"username\":\""
				+ username + "\", \"password\":\"" + password + "\", \"email\":\"" + email + "\", \"fullname\":\""
				+ fullname + "\", \"role\":\"" + role + "\", \"status\":\"" + status + "\"}";
	}
	
	
	
	
}
