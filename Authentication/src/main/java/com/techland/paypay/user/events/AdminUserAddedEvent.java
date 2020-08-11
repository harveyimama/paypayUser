package com.techland.paypay.user.events;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.TechLandEvent;
import com.techland.paypay.user.helper.Settings;

@Immutable
@TechLandEvent(externalName = "Admin.UserAddedEvent")
public class AdminUserAddedEvent implements PayPayEvent, Serializable {

	private static final long serialVersionUID = 1L;
	private final String id;
	private final String username;
	private final String password;
	private final String email;
	private final String fullname;
	private final String role;
	@SuppressWarnings("unused")
	private final String userId;
	private final Timestamp timestamp;
	private final String eventId;

	public AdminUserAddedEvent(String id, String username, String password, String email, String fullname, String role,String userId,
			 Timestamp timestamp, String eventId) {

		this.id = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
		this.timestamp = timestamp;
		this.eventId = eventId;
		this.userId = userId;
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

	@Override
	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public String getEventId() {
		return eventId;
	}

	@Override
	public String getObiquitusName() {
		return Settings.DOMAIN + "." + this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return "{\"class\":\"MerchantUserAddedEvent\",\"id\":\"" + id + "\", \"username\":\"" + username
				+ "\", \"password\":\"" + password + "\", \"email\":\"" + email + "\", \"fullname\":\"" + fullname
				+ "\", \"role\":\"" + role + "\", \"timestamp\":\"" + timestamp + "\", \"eventId\":\"" + eventId
				+ "\"}";
	}
	
	

}
