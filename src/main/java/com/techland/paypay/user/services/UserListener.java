package com.techland.paypay.user.services;

import java.util.List;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.techland.paypay.user.contracts.Listener;
import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.subscribers.Emailer;
import com.techland.paypay.user.subscribers.SubscriberFactory;

public class UserListener {

	@StreamListener(Constants.USEROUT)
	public <T extends UserEvent> void handleEvent(@Payload UserPayLoad<T> user) {

		pushToSubscribers(user.getUserEvent());

	}

	private <T extends UserEvent> void pushToSubscribers(final T userEvent) {

		List<Subscriber> subscribers = SubscriberFactory.getInstance(userEvent);

		for (Subscriber sub : subscribers) {

			if (sub.isState())
				sub.process(userState);
			else
				sub.process(userEvent);
		}

	}

}
