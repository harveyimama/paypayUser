package com.techland.paypay.user.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;

@Component
public class UserPayLoad<T extends UserEvent> {
	private T userEvent;
	private String eventId;
	private String userEventId;
	private  Timestamp timestamp;
	
	UserPayLoad()
	{
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.eventId = Settings.uniqueId();
	}
	
	public  T getUserEvent() {
		return this.userEvent;
	}
	public void setUserEvent( T userEvent) {
		this.userEvent = userEvent;
	}
	public String getEventId() {
		return eventId;
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
