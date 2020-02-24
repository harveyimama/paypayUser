package com.techland.paypay.user.users;


import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.StatusChangeCommand;
import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.events.UserStatusChangedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.helper.Status;
import com.techland.paypay.user.impl.UserPayLoad;
import com.techland.paypay.user.messaging.UserMessenger;
import com.techland.paypay.user.util.LogFeed;

@Component
public final class Customer implements User {

	
	private UserMessenger<UserAddedEvent> addUserMessenger;
	private UserMessenger<UserStatusChangedEvent> userStatusChangedessenger;
	private UserPayLoad<UserAddedEvent>  userAddedPayload;
	private UserPayLoad<UserStatusChangedEvent>  userStatusChangedPayload;
	private LogFeed logfeed;
	private UserStatusChangedEvent userStatusChangedEvent;


	public Customer( UserMessenger<UserAddedEvent> addUserMessenger, UserPayLoad<UserAddedEvent>  userAddedPayload
			,LogFeed logfeed,UserStatusChangedEvent userStatusChangedEvent,
			 UserPayLoad<UserStatusChangedEvent>  userStatusChangedPayload,
			 UserMessenger<UserStatusChangedEvent> userStatusChangedessenger) {

		this.addUserMessenger = addUserMessenger;
		this.userAddedPayload = userAddedPayload;
		this.logfeed = logfeed;
		this.userStatusChangedEvent = userStatusChangedEvent;
		this.userStatusChangedPayload = userStatusChangedPayload;
		this.userStatusChangedessenger = userStatusChangedessenger;
	}

	
	

	@Override
	public String openAccount(AddUserCommand user) {

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
		return userAddedPayload.getEventId();
		
	}


	



	@Override
	public void updateAccount() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void updateAccountStatus(StatusChangeCommand statusCommand) {
	try {

			ExecutorService executer = PayPayThread.startThreader();			
			userStatusChangedEvent.setStatus(statusCommand.getStatus().getName());				
	
			userStatusChangedPayload.setUserEvent(userStatusChangedEvent);
			userStatusChangedPayload.setUserEventId(statusCommand.getId());			
					
			executer.execute(new Runnable() {	
				@Override public void run() {
					userStatusChangedessenger.sendMessage(userStatusChangedPayload); 
				}
			} );
			
			logfeed.getInstance(Constants.SUCESS_MESSAGE,Customer.class,statusCommand.toString()).process();
					
		} catch (Exception e) {
			logfeed.getInstance(Constants.SERVER_ERROR,Customer.class,statusCommand.toString(),e.getMessage()).process();
			
		}
		
	}

}
