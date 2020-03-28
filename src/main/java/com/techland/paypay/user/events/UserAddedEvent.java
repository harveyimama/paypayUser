package com.techland.paypay.user.events;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.TechLandEvent;
import com.techland.paypay.user.helper.Settings;

@Immutable
@TechLandEvent(externalName = "User.UserAddedEvent")
public class UserAddedEvent implements PayPayEvent, Serializable {

	private static final long serialVersionUID = 1L;
	private final String userType;
	private final String id;
	private final String username;
	private final String password;
	private final String email;
	private final String fullname;
	private final String role;
	private final String status;
	private final Timestamp timestamp;
	private final String eventId;
	


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
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.eventId = Settings.aggregateTag();
	}
	
	public UserAddedEvent(String userType, String id, String username, String password, String email, String fullname,
			String role, String status,Timestamp timestamp,String eventId) {
		this.userType = userType;
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.timestamp = timestamp;
		this.eventId = eventId;
	}
	

	public String getUserType() {
		return userType;
	}
	@Override
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	@Override
	public String getEventId() {
		return eventId;
	}

	@Override
	public String toString() {
		return "{\"class\":\"UserAddedEvent\",\"userType\":\"" + userType + "\", \"id\":\"" + id + "\", \"username\":\""
				+ username + "\", \"password\":\"" + password + "\", \"email\":\"" + email + "\", \"fullname\":\""
				+ fullname + "\", \"role\":\"" + role + "\", \"status\":\"" + status + "\", \"timestamp\":\""
				+ timestamp + "\", \"eventId\":\"" + eventId + "\"}";
	}

	@Override
	public String getObiquitusName() {
		return Settings.DOMAIN+"."+this.getClass().getSimpleName();
	}
	
	
	
	

}
