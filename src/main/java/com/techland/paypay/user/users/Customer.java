package com.techland.paypay.user.users;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.techland.paypay.user.config.PayPayLogger;
import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.config.UserRepository;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.contracts.UserEvent;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Status;
import com.techland.paypay.user.impl.AddUserCommand;
import com.techland.paypay.user.impl.UserEventFactory;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.persistence.User;
import com.techland.paypay.user.services.UserMessenger;

public final class Customer implements User {

	
	private UserMessenger<UserAddedEvent> addUserMessenger;
	private UserPayLoad<UserAddedEvent>  userAddedPayload;
	


	public Customer( UserMessenger<UserAddedEvent> addUserMessenger, UserPayLoad<UserAddedEvent>  userAddedPayload) {

		this.addUserMessenger = addUserMessenger;
		this.userAddedPayload = userAddedPayload;
	
	
	}

	
	@Override
	public void updateAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openAccount(AddUserCommand user,String id) {

		ExecutorService executer = PayPayThread.startThreader();
		
		UserAddedEvent event =	new UserAddedEvent(user.getUserType(), user.getId(), user.getUsername(),
				user.getPassword(), user.getEmail(), user.getFullname(), user.getRole(), Status.EMAILNOTVERIFIED.getName());
	
		
		userAddedPayload.setEventId(id);
		userAddedPayload.setUserEvent(event);
		userAddedPayload.setUserEventId(user.getId());
		
				
		executer.execute(new Runnable() {	
			@Override public void run() {
				addUserMessenger.sendMessage(userAddedPayload); 
			}
		} );
		
	}


	@Override
	public void updateAccountStatus() {
		// TODO Auto-generated method stub

	}

}
