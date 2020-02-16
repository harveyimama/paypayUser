package com.techland.paypay.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.techland.paypay.user.impl.AddUserCommand;

@Entity
public class User {
	
	private AddUserCommand userCommand;
	
	User(AddUserCommand userCommand)
	{
		this.userCommand = userCommand;
	}
	
	@Id
	private String id;
	private  String userType;
	private  String username;
	private  String password;
	private  String email;
	private  String fullname;
	private  String role;
	private  String status;
	
	public void setCommand(AddUserCommand userCommand)
	{
		this.setEmail(userCommand.getEmail());
		this.setFullname(userCommand.getFullname());
		this.setId(userCommand.getId());
		this.setPassword(userCommand.getPassword());
		this.setRole(userCommand.getRole());
		this.setUsername(userCommand.getUsername());
		this.setUserType(userCommand.getUserType());
		
		
		
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
