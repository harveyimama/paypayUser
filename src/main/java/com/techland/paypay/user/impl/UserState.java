package com.techland.paypay.user.impl;


import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.events.UserStatusChangedEvent;
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
	
	
	public <T extends UserEvent> void addEvent(T event)
	{
		if (event instanceof UserAddedEvent)
		{
		this.setEmail(((UserAddedEvent) event).getEmail());
		this.setFullname(((UserAddedEvent) event).getFullname());
		this.setId(((UserAddedEvent) event).getId());
		this.setPassword(((UserAddedEvent) event).getPassword());
		this.setRole(((UserAddedEvent) event).getRole());
		this.setUsername(((UserAddedEvent) event).getUsername());
		this.setUserType(((UserAddedEvent) event).getUserType());
		this.setStatus(Status.EMAILNOTVERIFIED.getName());
		}
		
		if (event instanceof UserStatusChangedEvent)
		{
			this.setStatus(((UserStatusChangedEvent) event).getStatus());
		}
	}
	
	
}
