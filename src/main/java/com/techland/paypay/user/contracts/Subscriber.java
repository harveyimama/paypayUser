package com.techland.paypay.user.contracts;

import com.techland.paypay.user.impl.UserEvent;

/**
 * @author Harvey Imama
 * Implement and add to required listeners
 */

public interface Subscriber {
	
	/**
	 * 
	 *
	 */
	void process(String topic, UserEvent user);
	


}
