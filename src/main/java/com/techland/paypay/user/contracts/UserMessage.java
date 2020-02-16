package com.techland.paypay.user.contracts;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import com.techland.paypay.user.helper.Constants;

public interface UserMessage {
		
	
    @Input(Constants.USERIN)
    SubscribableChannel inbound();

    @Output(Constants.USEROUT)
    MessageChannel outbound();
    
  

}
