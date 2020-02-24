package com.techland.paypay.user.messaging;


import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.contracts.UserMessage;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.util.LogFeed;
import com.techland.paypay.user.util.MonitorFeed;

@Component
public class UserMessenger<T extends UserEvent> {
	private final UserMessage userMessage;
	private  MonitorFeed<T> monitorFeed;
	private LogFeed logfeed;
	
	public UserMessenger(UserMessage userMessage,LogFeed logfeed,MonitorFeed<T> monitorFeed)
	{
		this.userMessage = userMessage;
		this.logfeed = logfeed;
		this.monitorFeed= monitorFeed;
	}
	
    public void sendMessage(final UserPayLoad<T> payload) {

        try {
			MessageChannel messageChannel = userMessage.outbound();
     
			messageChannel.send(MessageBuilder
			        .withPayload(payload)     
			        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
			        .build(),Settings.ASYNC_WAIT_TIME);
			
			 monitorFeed.getInstance(payload,Constants.USERIN).process();
			 logfeed.getInstance(Constants.SUCESS_MESSAGE,UserMessenger.class,payload.toString()).process();
			
		} catch (Exception e) {
			
			 logfeed.getInstance(Constants.SERVER_ERROR,UserMessenger.class,payload.toString()).process();
		}
        
       

    }

}
