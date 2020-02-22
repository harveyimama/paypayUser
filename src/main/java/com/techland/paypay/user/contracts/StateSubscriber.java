package com.techland.paypay.user.contracts;

import com.techland.paypay.user.impl.UserState;
import com.techland.paypay.user.persistence.StateFailure;
import com.techland.paypay.user.persistence.StateFailureRepository;

public interface StateSubscriber extends Subscriber {
	
	
	public <T extends UserState> void process( T userState);
	
	public <T extends UserState> void handleError( T userState,StateFailure failure,StateFailureRepository failureRepo);
	
	public <T extends UserState> void handleSuccess( T userState,StateFailure failure,StateFailureRepository failureRepo);

}
