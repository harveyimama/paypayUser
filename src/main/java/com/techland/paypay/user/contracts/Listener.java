package com.techland.paypay.user.contracts;

import org.springframework.messaging.handler.annotation.Payload;

import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.impl.UserEvent;

public interface Listener {
	
	 public void handleEvent(@Payload UserEvent user);
	 
	  default void pushToSubscribers( UserEvent user,String type,Subscriber... subscribers) {

			for (Subscriber sub : subscribers) {
				sub.process(type, user);
			}
		}

}
