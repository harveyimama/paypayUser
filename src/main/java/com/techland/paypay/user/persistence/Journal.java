package com.techland.paypay.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.techland.paypay.user.contracts.UserEvent;

@Entity
public class Journal {
	
	@Id
	String eventId;
	UserEvent userEvent;	
	String userId;
	
		
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public UserEvent getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(UserEvent userEvent) {
		this.userEvent = userEvent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
