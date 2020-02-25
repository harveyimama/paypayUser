package com.techland.paypay.user.events;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;

@Component
public final class UserStatusChangedEvent implements UserEvent, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String status;
	private final Timestamp timestamp;
	private final String eventId;

	public UserStatusChangedEvent(String status) {
		this.status = status;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.eventId = Settings.uniqueId();
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getEventId() {
		return eventId;
	}

	@Override
	public String toString() {
		return "{\"class\":\"UserStatusChangedEvent\",\"status\":\"" + status + "\", \"timestamp\":\"" + timestamp
				+ "\", \"eventId\":\"" + eventId + "\"}";
	}

	

}
