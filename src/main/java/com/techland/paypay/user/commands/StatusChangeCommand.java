package com.techland.paypay.user.commands;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.helper.Status;
@Component
public class StatusChangeCommand {
	
	private String id;
	private Status status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", status\":\"" + status.getName() + "}";
	}


	
	

}
