package com.techland.paypay.user.contracts;

import org.springframework.messaging.handler.annotation.Payload;

import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;

public interface Listener {
	
	 public void handleEvent(@Payload UserAddedEvent user);
	 
	  default void pushToSubscribers( UserAddedEvent user,String type,Subscriber... subscribers) {

			for (Subscriber sub : subscribers) {
				sub.process(type, user);
			}
		}

}
