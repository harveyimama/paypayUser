package com.techland.paypay.user.usertypes;

import java.util.concurrent.ExecutorService;

import com.techland.paypay.user.config.PayPayLogger;
import com.techland.paypay.user.config.PayPayThread;
import com.techland.paypay.user.config.UserRepository;
import com.techland.paypay.user.contracts.UserType;
import com.techland.paypay.user.entity.User;
import com.techland.paypay.user.helper.Status;
import com.techland.paypay.user.impl.UserCommand;
import com.techland.paypay.user.impl.UserEvent;
import com.techland.paypay.user.services.AddUserMessenger;

public final class Customer implements UserType {

	private final UserRepository userRepo;
	private AddUserMessenger addUserMessenger;
	private User userEntity;
	private PayPayLogger paypayLogger;


	public Customer(UserRepository userRepo, User userEntity, AddUserMessenger addUserMessenger,PayPayLogger paypayLogger) {
		this.userRepo = userRepo;
		this.userEntity = userEntity;
		this.addUserMessenger = addUserMessenger;
		this.paypayLogger = paypayLogger;
	}

	
	@Override
	public void updateAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openAccount(UserCommand user) {

		ExecutorService executer = PayPayThread.startThreader();
		userEntity.setCommand(user);
		userEntity.setStatus(Status.PENDING);
		
		executer.execute(new Runnable() {	
			@Override public void run() {
			userRepo.save(userEntity);
			}
		} );
		
		executer.execute(new Runnable() {	
			@Override public void run() {
				addUserMessenger.sendMessage(new UserEvent(user.getUserType(), user.getId(), user.getUsername(),
						user.getPassword(), user.getEmail(), user.getFullname(), user.getRole(), Status.PENDING));
			}
		} );
		
		paypayLogger.doLog("openAccount", userEntity.toString());	
	}


	@Override
	public void updateAccountStatus() {
		// TODO Auto-generated method stub

	}

}
