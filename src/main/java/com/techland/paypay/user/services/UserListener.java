package com.techland.paypay.user.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserEntity;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.impl.UserState;
import com.techland.paypay.user.subscribers.SubscriberFactory;
import com.techland.paypay.user.util.LogFeed;
import com.techland.paypay.user.util.Logger;
import com.techland.paypay.user.util.Monitor;
import com.techland.paypay.user.util.MonitorFeed;

public class UserListener {
	private UserEntity entity;

	private UserListener(UserEntity entity) {
		this.entity = entity;
	}

	@StreamListener(Constants.USEROUT)
	public <T extends UserEvent> void handleEvent(@Payload UserPayLoad<T> payload) {

		try {

			entity.addEvent(payload.getUserEventId(), payload.getUserEvent(), payload.getEventId());
			pushToSubscribers(payload);

			Monitor.process(new MonitorFeed(payload.getTimestamp(), new Timestamp(System.currentTimeMillis()),
					Constants.USEROUT, payload.getUserEvent().getClass().getSimpleName()));

			Logger.process(new LogFeed(Constants.SUCESS_MESSAGE, Constants.USEROUT,
					payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(), payload.getUserEventId()));

		} catch (Exception e) {

			Logger.process(new LogFeed(Constants.SERVER_ERROR, Constants.USEROUT,
					payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(), payload.getUserEventId()));
		}
	}

	private <T extends UserEvent> void pushToSubscribers(final UserPayLoad<T> user) {

		List<Subscriber> subscribers = SubscriberFactory.getInstance(user.getUserEvent());

		UserState userState = entity.getState(user.getUserEventId());

		for (Subscriber sub : subscribers) {

			if (sub.isState())
				processSubscriber(user, sub);
			else
				processSubscriber(user, sub, userState);
		}

	}

	private <T extends UserEvent> void processSubscriber(UserPayLoad<T> payload, Subscriber sub) {
		ExecutorService executer = PayPayThread.startThreader();

		executer.execute(new Runnable() {
			@Override
			public void run() {

				try {

					sub.process(payload.getUserEvent());

					Monitor.process(new MonitorFeed(payload.getTimestamp(), new Timestamp(System.currentTimeMillis()),
							sub.getClass().getSimpleName(), payload.getUserEvent().getClass().getSimpleName()));

					Logger.process(new LogFeed(Constants.SUCESS_MESSAGE, sub.getClass().getSimpleName(),
							payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(),
							payload.getUserEventId()));

				} catch (Exception e) {
					Logger.process(new LogFeed(Constants.SERVER_ERROR, sub.getClass().getSimpleName(),
							payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(),
							payload.getUserEventId()));
				}
			}
		});
	}

	private <T extends UserEvent> void processSubscriber(UserPayLoad<T> payload, Subscriber sub, UserState state) {
		ExecutorService executer = PayPayThread.startThreader();

		executer.execute(new Runnable() {
			@Override
			public void run() {

				try {

					sub.process(state);

					Monitor.process(new MonitorFeed(payload.getTimestamp(), new Timestamp(System.currentTimeMillis()),
							sub.getClass().getSimpleName(), payload.getUserEvent().getClass().getSimpleName()));

					Logger.process(new LogFeed(Constants.SUCESS_MESSAGE, sub.getClass().getSimpleName(),
							payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(),
							payload.getUserEventId()));

				} catch (Exception e) {
					Logger.process(new LogFeed(Constants.SERVER_ERROR, sub.getClass().getSimpleName(),
							payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(),
							payload.getUserEventId()));
				}
			}
		});
	}
}
