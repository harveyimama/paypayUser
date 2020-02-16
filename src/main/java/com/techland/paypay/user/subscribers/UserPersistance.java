package com.techland.paypay.user.subscribers;

import com.techland.paypay.user.contracts.Subscriber;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.impl.UserState;

public class UserPersistance  implements Subscriber {

	@Override
	public boolean isState() {
		return true;
	}

	@Override
	public <T extends UserEvent> void process(T userEvent) {
		}

	@Override
	public <T extends UserState> void process(T userState) {
		// TODO Auto-generated method stub
		
	}

}
