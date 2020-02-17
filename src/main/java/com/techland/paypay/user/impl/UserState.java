package com.techland.paypay.user.impl;

import java.io.Serializable;

import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Status;

public class UserState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private  String userType;
	private  String username;
	private  String password;
	private  String email;
	private  String fullname;
	private  String role;
	private  String status;
	
	public void addEvent(UserAddedEvent event)
	{
		this.setEmail(event.getEmail());
		this.setFullname(event.getFullname());
		this.setId(event.getId());
		this.setPassword(event.getPassword());
		this.setRole(event.getRole());
		this.setUsername(event.getUsername());
		this.setUserType(event.getUserType());
		this.setStatus(Status.EMAILNOTVERIFIED.getName());
	}
	
	
	
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
	
	
}
