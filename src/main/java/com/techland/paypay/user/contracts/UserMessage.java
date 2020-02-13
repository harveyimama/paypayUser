package com.techland.paypay.user.contracts;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import com.techland.paypay.user.helper.Constants;

public interface UserMessage {
		
	
    @Input(Constants.ADD_USER)
    SubscribableChannel inboundAddUser();

    @Output(Constants.ADD_USER)
    MessageChannel outboundAddUser();
    
    @Input(Constants.SEND_EMAIl)
    SubscribableChannel inboundSendEmail();

    @Output(Constants.SEND_EMAIl)
    MessageChannel outboundSendEmail();
    
    @Output(Constants.SEND_MONITOR)
    MessageChannel outboundSendMonitor();
    
   

}
