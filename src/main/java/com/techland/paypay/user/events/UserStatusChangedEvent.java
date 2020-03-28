package com.techland.paypay.user.events;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.Immutable;

import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.TechLandEvent;
import com.techland.paypay.user.helper.Settings;

@Immutable
@TechLandEvent(externalName = "User.UserStatusChangedEvent")
public final class UserStatusChangedEvent implements PayPayEvent, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String status;
	private final Timestamp timestamp;
	private final String eventId;
	private final String id;

	public UserStatusChangedEvent(String status,String id) {
		this.status = status;
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.eventId = Settings.aggregateTag();
		this.id = id;
	}
	
	public UserStatusChangedEvent(String status,Timestamp timestamp,String eventId,String id) {
		this.status = status;
		this.timestamp = timestamp;
		this.eventId = eventId;
		this.id = id;
	}

	public String getStatus() {
		return status;
	}
	@Override
	public Timestamp getTimestamp() {
		return timestamp;
	}
	@Override
	public String getEventId() {
		return eventId;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "{\"class\":\"UserStatusChangedEvent\",\"status\":\"" + status + "\", \"timestamp\":\"" + timestamp
				+ "\", \"eventId\":\"" + eventId + "\", \"id\":\"" + id + "\"}";
	}
	
	@Override
	public String getObiquitusName() {
		return Settings.DOMAIN+"."+this.getClass().getSimpleName();
	}
	


	
	

}
