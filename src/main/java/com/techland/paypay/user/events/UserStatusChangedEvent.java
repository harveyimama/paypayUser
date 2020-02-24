package com.techland.paypay.user.events;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.contracts.UserEvent;
@Component
public final class UserStatusChangedEvent  implements UserEvent,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{\"class\":\"UserStatusChangedEvent\",\"status\":\"" + status + "\"}";
	}

	
	
	
	
}
