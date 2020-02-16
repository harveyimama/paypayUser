package com.techland.paypay.user.services;

import java.sql.Timestamp;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.contracts.UserMessage;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.util.LogFeed;
import com.techland.paypay.user.util.Logger;
import com.techland.paypay.user.util.Monitor;
import com.techland.paypay.user.util.MonitorFeed;

@Service
public class UserMessenger<T extends UserEvent> {
	private final UserMessage userMessage;
	
	public UserMessenger(UserMessage userMessage)
	{
		this.userMessage = userMessage;
	}
	
    public void sendMessage(final UserPayLoad<T> payload) {

        try {
			MessageChannel messageChannel = userMessage.outbound();
     
			messageChannel.send(MessageBuilder
			        .withPayload(payload)     
			        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
			        .build(),Settings.ASYNC_WAIT_TIME);
			
			Monitor.process(new MonitorFeed(payload.getTimestamp(), new Timestamp(System.currentTimeMillis()),Constants.USEROUT,
					payload.getUserEvent().getClass().getSimpleName()));
			
			 Logger.process(new LogFeed(Constants.SUCESS_MESSAGE, this.getClass().getSimpleName(),
						payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(), payload.getUserEventId()));
			
		} catch (Exception e) {
			
			 Logger.process(new LogFeed(Constants.SERVER_ERROR, this.getClass().getSimpleName(),
						payload.getUserEvent().getClass().getSimpleName(), payload.getEventId(), payload.getUserEventId()));
		}
        
       

    }

}
