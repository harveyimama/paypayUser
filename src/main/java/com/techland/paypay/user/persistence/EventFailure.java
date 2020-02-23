package com.techland.paypay.user.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.techland.paypay.user.contracts.UserEvent;
@Entity
public class EventFailure implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String subscriber;
	private  UserEvent event;
	
	
	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}
	public UserEvent getEvent() {
		return event;
	}
	public void setEvent(UserEvent event) {
		this.event = event;
	}
	
	
	
	

}
