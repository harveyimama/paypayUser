package com.techland.paypay.user.contracts;

import com.techland.paypay.user.persistence.EventFailure;
import com.techland.paypay.user.persistence.EventFailureRepository;

public interface EventSubscriber extends Subscriber{
	
	public <T extends UserEvent> void process( T userEvent);

	public <T extends UserEvent> void handleError( T userEvent,EventFailure failure,EventFailureRepository failureRepo);
}
