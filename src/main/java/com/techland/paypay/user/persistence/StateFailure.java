package com.techland.paypay.user.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.techland.paypay.user.contracts.UserEvent;
@Entity
public class StateFailure implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String userId;
	private String subscriber;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}
	
	
	
	
	

}
