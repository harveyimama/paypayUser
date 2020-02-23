package com.techland.paypay.user.events;

import java.io.Serializable;

import org.hibernate.annotations.Immutable;

import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.persistence.User;

@Immutable
public class UserAddedEvent implements UserEvent,Serializable  {
	

private static final long serialVersionUID = 1L;
private final String userType;
private final String id;
private final String username;
private final String password;
private final String email;
private final String fullname;
private final String role;
private final String status;



public UserAddedEvent(String userType, String id, String username, String password, String email, String fullname,
		String role, String status) {
	this.userType = userType;
	this.id = id;
	this.username = username;
	this.password = password;
	this.email = email;
	this.fullname = fullname;
	this.role = role;
	this.status = status;
}



public String getUserType() {
	return userType;
}


public String getId() {
	return id;
}


public String getUsername() {
	return username;
}


public String getPassword() {
	return password;
}


public String getEmail() {
	return email;
}


public String getFullname() {
	return fullname;
}


public String getRole() {
	return role;
}


public String getStatus() {
	return status;
}



@Override
public String toString() {
	return "{\"userType\":\"" + userType + "\", id\":\"" + id + "\", username\":\"" + username + "\", password\":\""
			+ password + "\", email\":\"" + email + "\", fullname\":\"" + fullname + "\", role\":\"" + role
			+ "\", status\":\"" + status + "}";
}



}
