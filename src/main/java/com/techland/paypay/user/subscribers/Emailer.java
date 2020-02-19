package com.techland.paypay.user.subscribers;

import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.impl.UserState;
import com.techland.paypay.user.responses.ServiceResponse;


public final class Emailer implements Subscriber {
	
	private ServiceResponse resp;
	
	Emailer (ServiceResponse resp)
	{
		this.resp = resp ;
	}
	
	@Override
	public boolean isState() {
			return false;
	}

	@Override
	public <T extends UserEvent> void process(T userEvent) {
		if(userEvent instanceof UserAddedEvent)
		sendVericiationEmail();		
	}
	
	private ServiceResponse sendVericiationEmail() {
		System.out.print("Sending veirifcation Email");
		return resp;
	}

	@Override
	public <T extends UserState> void process(T userState) {
		
	}
	
	

}
