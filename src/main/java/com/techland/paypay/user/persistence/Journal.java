package com.techland.paypay.user.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.techland.paypay.user.contracts.UserEvent;

@Entity
public class Journal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(columnDefinition = "serial")
	private String eventId;
	private UserEvent userEvent;	
	private String userId;
	
		
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
