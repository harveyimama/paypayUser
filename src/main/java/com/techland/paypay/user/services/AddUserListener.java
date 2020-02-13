package com.techland.paypay.user.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.techland.paypay.user.contracts.Listener;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserEvent;
import com.techland.paypay.user.subscribers.Emailer;
import com.techland.paypay.user.subscribers.Monitor;
import com.techland.paypay.user.subscribers.UserLogger;

public class AddUserListener implements Listener {

	final Emailer emailer;


	AddUserListener(Emailer emailer) {
		this.emailer = emailer;
	}

	@StreamListener(Constants.ADD_USER)
	public void handleEvent(@Payload UserEvent user) {

		pushToSubscribers(user,Constants.ADD_USER,emailer);
	
	}


	
}
