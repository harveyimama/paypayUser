package com.techland.paypay.user.subscribers;

import org.springframework.stereotype.Component;

import com.techland.paypay.user.contracts.StateSubscriber;
import com.techland.paypay.user.impl.UserState;
import com.techland.paypay.user.persistence.StateFailure;
import com.techland.paypay.user.persistence.StateFailureRepository;
import com.techland.paypay.user.persistence.User;
import com.techland.paypay.user.persistence.UserRepository;
@Component
public class UserPersistance  implements StateSubscriber {
	
	private User user;
	private UserRepository userRepo;
	private StateFailure failure;
	private StateFailureRepository failureRepo;
	
	public UserPersistance (User user,UserRepository userRepo,StateFailure failure,StateFailureRepository failureRepo)
	{
		this.user = user;
		this.userRepo = userRepo;
		this.failure = failure;
		this.failureRepo = failureRepo;
	}

	@Override
	public boolean isState() {
		return true;
	}

	
	@Override
	public <T extends UserState> void process(final T userState) {
				
		try {
			
			user  = setEntity(userState);
			User ret = userRepo.save(user);
			
			if(ret == null)
				handleError(userState,failure,failureRepo);
			else
				handleSuccess(userState,failure,failureRepo);
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(userState,failure,failureRepo);
		}	
		
	}
	
	private <T extends UserState> User setEntity(final T userState)
	{
		user.setEmail(userState.getEmail());
		user.setFullname(userState.getFullname());
		user.setId(userState.getId());
		user.setPassword(userState.getPassword());
		user.setRole(userState.getRole());
		user.setStatus(userState.getStatus());
		user.setUsername(userState.getUsername());
		user.setUserType(userState.getUserType());
		
		return user;
	}

	@Override
	public <T extends UserState> void handleError(T userState, StateFailure failure, StateFailureRepository failureRepo) {
		failure.setStateUserId(userState.getId());
		failure.setStateSubscriber(this.getClass().getSimpleName());
		failureRepo.save(failure);
	}

	@Override
	public <T extends UserState> void handleSuccess(T userState, StateFailure failure, StateFailureRepository failureRepo) {
		failure.setStateUserId(userState.getId());
		failure.setStateSubscriber(this.getClass().getSimpleName());
		failureRepo.delete(failure);
	}

	

}
