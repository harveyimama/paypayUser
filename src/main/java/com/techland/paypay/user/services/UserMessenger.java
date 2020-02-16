package com.techland.paypay.user.services;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.contracts.UserMessage;
import com.techland.paypay.user.impl.UserPayLoad;

@Service
public class UserMessenger<T extends UserEvent> {
	private final UserMessage userMessage;
	
	public UserMessenger(UserMessage userMessage)
	{
		this.userMessage = userMessage;
	}
	
    public void sendMessage(final UserPayLoad<T> payload) {

        MessageChannel messageChannel = userMessage.outbound();
      
        messageChannel.send(MessageBuilder
                .withPayload(payload)     
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build(),Settings.ASYNC_WAIT_TIME);

    }

}
