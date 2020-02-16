package com.techland.paypay.user.impl;

import java.sql.Timestamp;

import com.techland.paypay.user.contracts.UserEvent;

public class UserPayLoad<T extends UserEvent> {
	private T userEvent;
	private String eventId;
	private String userEventId;
	private  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	
	public  T getUserEvent() {
		return this.userEvent;
	}
	public void setUserEvent( T userEvent) {
		this.userEvent = userEvent;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserEventId() {
		return userEventId;
	}
	public void setUserEventId(String userEventId) {
		this.userEventId = userEventId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	
	
	
	

}
