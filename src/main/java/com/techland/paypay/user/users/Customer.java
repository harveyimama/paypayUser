package com.techland.paypay.user.users;


import java.util.concurrent.ExecutorService;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.helper.Status;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.services.UserMessenger;
import com.techland.paypay.user.util.LogFeed;


public final class Customer implements User {

	
	private UserMessenger<UserAddedEvent> addUserMessenger;
	private UserPayLoad<UserAddedEvent>  userAddedPayload;
		private LogFeed logfeed;


	public Customer( UserMessenger<UserAddedEvent> addUserMessenger, UserPayLoad<UserAddedEvent>  userAddedPayload
			,LogFeed logfeed) {

		this.addUserMessenger = addUserMessenger;
		this.userAddedPayload = userAddedPayload;
		this.logfeed = logfeed;
	}

	
	@Override
	public void updateAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openAccount(AddUserCommand user) {

		try {
			
			ExecutorService executer = PayPayThread.startThreader();
			
			UserAddedEvent event =	new UserAddedEvent(user.getUserType(), user.getId(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getFullname(), user.getRole(), Status.EMAILNOTVERIFIED.getName());

			
	
			userAddedPayload.setUserEvent(event);
			userAddedPayload.setUserEventId(user.getId());
			
					
			executer.execute(new Runnable() {	
				@Override public void run() {
					addUserMessenger.sendMessage(userAddedPayload); 
				}
			} );
			
			logfeed.getInstance(Constants.SUCESS_MESSAGE,Customer.class,user.toString()).process();
			
			
		} catch (Exception e) {
			logfeed.getInstance(Constants.SERVER_ERROR,Customer.class,user.toString(),e.getMessage()).process();
			
		}
		
	}


	@Override
	public void updateAccountStatus() {
		// TODO Auto-generated method stub

	}

}
