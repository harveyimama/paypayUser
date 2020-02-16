package com.techland.paypay.user.contracts;

import com.techland.paypay.user.impl.UserState;

/**
 * @author Harvey Imama
 * Implement and add to required listeners
 */

public interface Subscriber {
	
	boolean isState();
		
	public <T extends UserEvent> void process( T userEvent);
	
	public <T extends UserState> void process( T userState);



}
