package com.techland.paypay.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class StateFailure  {

	
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
