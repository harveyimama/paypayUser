package com.techland.paypay.user.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techland.paypay.Constants;
import com.techland.paypay.PayPayPayLoad;
import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.Subscriber;
import com.techland.paypay.messaging.PayPayListener;
import com.techland.paypay.user.factories.EventFactory;
import com.techland.paypay.user.factories.SubscriberFactory;
import com.techland.paypay.user.helper.Settings;
import com.techland.paypay.util.LogFeed;

@Component
public class UserListener {
	private PayPayListener listenerTools;
	private SubscriberFactory subscriberFactory;

	public UserListener(final PayPayListener listenerTools, final SubscriberFactory subscriberFactory) {
		this.listenerTools = listenerTools;
		this.subscriberFactory = subscriberFactory;
	}

	@StreamListener(target = Constants.IN)
	public void handleEvent(@Payload PayPayPayLoad payload) {
		System.out.println("User listening .......");
		try {
			boolean isSelfOriginated = false;
			PayPayEvent event = EventFactory.getEvent(payload.getEvent(), payload.getEventName());
			if (payload.getEventName().contains(Settings.DOMAIN))
				isSelfOriginated = true;

			ConcurrentHashMap<String, List<? extends Subscriber>> subscribers = subscriberFactory.getInstance(event);
			listenerTools.handleEvent(payload, subscribers, event, new UserState(),isSelfOriginated);

		} catch (Exception e) {
			e.printStackTrace();
			LogFeed logfeed = new LogFeed.LogProcessor().setInfo(Constants.SERVER_ERROR).setClazz(PayPayListener.class)
					.setDomain(Settings.DOMAIN).setOtherInfo(payload.toString()).build();
			logfeed.process();

		}

	}
}
