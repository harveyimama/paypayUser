package com.techland.paypay.user.messaging;

import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.contracts.EventSubscriber;
import com.techland.paypay.user.contracts.StateSubscriber;
import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserEntity;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.impl.UserState;
import com.techland.paypay.user.subscribers.SubscriberFactory;
import com.techland.paypay.user.util.LogFeed;
import com.techland.paypay.user.util.MonitorFeed;

@Component
public class UserListener {
	private UserEntity entity;
	private LogFeed logfeed;
	private MonitorFeed monitorFeed ;
	private SubscriberFactory subscriberFactory;
	

	public UserListener(final UserEntity entity,final LogFeed logfeed,final MonitorFeed monitorFeed,SubscriberFactory subscriberFactory ) {
		this.entity = entity;
		this.logfeed = logfeed;
		this.monitorFeed = monitorFeed;
		this.subscriberFactory = subscriberFactory;
	}

	@StreamListener(target = Constants.USERIN)
	public void handleEvent(@Payload UserPayLoad payload) {
		System.out.println("Im listening .......");
		System.out.println(payload.toString());

		try {

			boolean ret  = entity.addEvent(payload.getUserEvent().getId(), payload.getUserEvent(), payload.getUserEvent().getEventId());
			
			if(ret)
			{
			pushToSubscribers(payload, monitorFeed);
			monitorFeed.getInstance(payload, Constants.USEROUT).process();
			logfeed.getInstance(Constants.SUCESS_MESSAGE, UserListener.class, payload.toString()).process();
			}
			else
			{
				monitorFeed.getInstance(payload, Constants.USEROUT).process();
				logfeed.getInstance(Constants.SOURCE_ERROR, UserListener.class, payload.toString()).process();	
			}

		} catch (Exception e) {
			e.printStackTrace();
			logfeed.getInstance(Constants.SERVER_ERROR, UserListener.class, payload.toString(), e.getMessage())
					.process();

		}
	}

	private  void pushToSubscribers(final UserPayLoad user, MonitorFeed monitorFeed) {

		List<Subscriber> subscribers = subscriberFactory.getInstance(user.getUserEvent());

		UserState userState = entity.getState(user.getUserEvent().getId());
		
		for (Subscriber sub : subscribers) {

			if (sub.isState())
				processSubscriber(user, sub, monitorFeed);
			else
				processSubscriber(user, sub, userState, monitorFeed);
		}

	}

	private  void processSubscriber(UserPayLoad payload, Subscriber sub,
			MonitorFeed monitorFeed) {
		ExecutorService executer = PayPayThread.startThreader();

		executer.execute(new Runnable() {
			@Override
			public void run() {

				try {

					 sub.process(payload.getUserEvent());
					monitorFeed.getInstance(payload, sub.getClass().getSimpleName()).process();
					logfeed.getInstance(Constants.SUCESS_MESSAGE, sub.getClass(), payload.toString()).process();

				} catch (Exception e) {

					logfeed.getInstance(Constants.SERVER_ERROR, sub.getClass(), payload.toString(), e.getMessage())
							.process();
				}
			}
		});
	}

	private <T extends UserEvent> void processSubscriber(UserPayLoad payload, Subscriber sub, UserState state,
			MonitorFeed monitorFeed) {
		ExecutorService executer = PayPayThread.startThreader();

		executer.execute(new Runnable() {
			@Override
			public void run() {

				try {
					sub.process(state);
					monitorFeed.getInstance(payload, sub.getClass().getSimpleName()).process();
					logfeed.getInstance(Constants.SUCESS_MESSAGE, sub.getClass(), payload.toString()).process();
				} catch (Exception e) {
					logfeed.getInstance(Constants.SERVER_ERROR, sub.getClass(), payload.toString(), e.getMessage())
							.process();
				}
			}
		});
	}
}
