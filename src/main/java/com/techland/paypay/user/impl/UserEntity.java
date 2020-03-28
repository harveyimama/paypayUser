package com.techland.paypay.user.impl;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techland.paypay.PayPayPayLoad;
import com.techland.paypay.config.PayPayThread;
import com.techland.paypay.contracts.PayPayEvent;
import com.techland.paypay.messaging.PayPayMessenger;
import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.StatusChangeCommand;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.events.UserStatusChangedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.helper.Settings;
import com.techland.paypay.user.helper.Status;
import com.techland.paypay.util.LogFeed;

@Component
public final class UserEntity {
	@Autowired
	private PayPayMessenger userMessenger;

	public void handleCommand(AddUserCommand user) {
			UserAddedEvent added = new UserAddedEvent(user.getUserType(), user.getId(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getFullname(), user.getRole(),
					Status.EMAILNOTVERIFIED.getName());

			handleEvent(added);
			LogFeed logfeed = new LogFeed.LogProcessor()  
					.setInfo(Constants.SUCESS_MESSAGE)
					.setClazz(UserEntity.class)
					.setDomain(Settings.DOMAIN)
					.setOtherInfo(user.toString())
					.build(); 
			logfeed.process();

	}

	public <T extends PayPayEvent> void handleEvent(T event) {

		PayPayPayLoad userPayload = new PayPayPayLoad.PayLoadBuilder().setDomain(Settings.DOMAIN).setEvent(event.toString())
				.setEventName(event.getObiquitusName()).build();

		ExecutorService executer = PayPayThread.startThreader();
		executer.execute(new Runnable() {
			@Override
			public synchronized void run() {
				userMessenger.sendMessage(userPayload);
			}
		});

	}

	public void handleCommand(StatusChangeCommand statusCommand) {
		
			UserStatusChangedEvent userStatusChangedEvent = new UserStatusChangedEvent(
					statusCommand.getStatus().getName(), statusCommand.getId());

			handleEvent(userStatusChangedEvent);
			LogFeed logfeed = new LogFeed.LogProcessor()  
					.setInfo(Constants.SUCESS_MESSAGE)
					.setClazz(UserEntity.class)
					.setDomain(Settings.DOMAIN)
					.setOtherInfo(statusCommand.toString())
					.build(); 
			logfeed.process();

	}

}
