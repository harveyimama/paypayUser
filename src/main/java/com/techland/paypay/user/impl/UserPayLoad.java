package com.techland.paypay.user.impl;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.events.UserAddedEvent;

@Component
public class UserPayLoad {
	private UserAddedEvent userEvent;


	public UserAddedEvent getUserEvent() {
		return userEvent;
	}

	public void setUserEvent(UserAddedEvent userEvent) {
		this.userEvent = userEvent;
	}

	

}
