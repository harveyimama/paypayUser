package com.techland.paypay.user.services;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.UserMessage;
import com.techland.paypay.user.impl.UserEvent;

@Service
public class AddUserMessenger {
	private final UserMessage userMessage;
	
	public AddUserMessenger(UserMessage userMessage)
	{
		this.userMessage = userMessage;
	}
	
    public void sendMessage(final UserEvent user) {

        MessageChannel messageChannel = userMessage.outboundAddUser();
      
        messageChannel.send(MessageBuilder
                .withPayload(user)        
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build(),Settings.ASYNC_WAIT_TIME);

    }

}
