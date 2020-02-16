package com.techland.paypay.user.contracts;

import com.techland.paypay.user.events.UserAddedEvent;

/**
 * @author Harvey Imama
 * Implement and add to required listeners
 */

public interface Subscriber {
	
	/**
	 * 
	 *
	 */
	void process(String topic, UserAddedEvent user);
	
	boolean isState();
	


}
