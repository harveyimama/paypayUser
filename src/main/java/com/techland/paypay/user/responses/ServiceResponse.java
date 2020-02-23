package com.techland.paypay.user.responses;

public class ServiceResponse {
	
	private boolean success;
	private int responseCode;
	private String messaged;
	private String eventId;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessaged() {
		return messaged;
	}
	public void setMessaged(String messaged) {
		this.messaged = messaged;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	

}
