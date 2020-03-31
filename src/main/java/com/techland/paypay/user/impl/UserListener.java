package com.techland.paypay.user.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techland.paypay.Constants;
import com.techland.paypay.PayPayPayLoad;
import com.techland.paypay.Settings;
import com.techland.paypay.aggregates.EventSourcer;
import com.techland.paypay.annotations.EventFactory;
import com.techland.paypay.annotations.SubscriberFactory;
import com.techland.paypay.config.PayPayThread;
import com.techland.paypay.contracts.EventSubscriber;
import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.contracts.PayPayState;
import com.techland.paypay.contracts.StateSubscriber;
import com.techland.paypay.contracts.Subscriber;
import com.techland.paypay.messaging.PayPayListener;
import com.techland.paypay.util.LogFeed;
import com.techland.paypay.util.MonitorFeed;

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

		System.out.println("Im listening .......");
		System.out.println(payload.getEvent());
		System.out.println(payload.getEventName());
		boolean ret = false;
		try {

			PayPayEvent event = EventFactory.getEvent(payload.getEvent(), payload.getEventName());

			if (event != null) {

				ConcurrentHashMap<String, List<? extends Subscriber>> subscribers = subscriberFactory
						.getInstance(event);
				System.out.println(event.getId());

				System.out.println(event.getEventId());

				listenerTools.handleEvent(payload, subscribers, event, new UserState());
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogFeed logfeed = new LogFeed.LogProcessor().setInfo(Constants.SERVER_ERROR).setClazz(PayPayListener.class)
					.setDomain(Settings.DOMAIN).setOtherInfo(payload.toString()).build();
			logfeed.process();

		}

	}
}
