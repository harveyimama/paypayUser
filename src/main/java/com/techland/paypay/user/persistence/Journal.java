package com.techland.paypay.user.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Journal  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(columnDefinition = "serial")
	private String eventId;
	private String userEvent;	
	private String userId;
	
		
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(String userEvent) {
		this.userEvent = userEvent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
