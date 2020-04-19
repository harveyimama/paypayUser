package com.techland.paypay.user.subscribers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.techland.paypay.contracts.PayPayState;
import com.techland.paypay.contracts.StateSubscriber;
import com.techland.paypay.contracts.TechLandSubscriber;
import com.techland.paypay.persistence.StateFailure;
import com.techland.paypay.persistence.StateFailureRepository;
import com.techland.paypay.user.impl.UserState;
import com.techland.paypay.user.persistence.User;
import com.techland.paypay.user.persistence.UserRepository;
@Component
@TechLandSubscriber(events = {"MerchantUserAddedEvent"},isstate=true)
public class UserPersistance  implements StateSubscriber {
	@Autowired
	private User user;
	@Autowired
	private StateFailure failure;
	@Autowired
	private StateFailureRepository failureRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean isState() {
		return true;
	}

	
	@Override
	public void process(final PayPayState userState) {
				
		try {
		
			User user  = setEntity((UserState) userState);
			User ret = userRepo.save(user);	
			
			if(ret == null)
				handleError(userState,failure,failureRepo);
			else
				handleSuccess(userState,failure,failureRepo);
			
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			
		} catch (Exception r)	
		{
			r.printStackTrace();
			handleError(userState,failure,failureRepo);
		}
		
	}
	
	private  User setEntity(final UserState userState)
	{
		user.setEmail(userState.getEmail());
		user.setFullname(userState.getFullname());
		user.setId(userState.getId());
		user.setPassword(userState.getPassword());
		user.setRole(userState.getRole());
		user.setStatus(userState.getStatus());
		user.setUsername(userState.getUsername());
		
		return user;
	}

	@Override
	public   void handleError(PayPayState userState, StateFailure failure, StateFailureRepository failureRepo) {
		failure.setStateUserId(((UserState) userState).getId());
		failure.setStateSubscriber(this.getClass().getSimpleName());
	
		failureRepo.save(failure);
		
	}

	@Override
	public  void handleSuccess(PayPayState userState, StateFailure failure, StateFailureRepository failureRepo) {
		failure.setStateUserId(((UserState) userState).getId());
		failure.setStateSubscriber(this.getClass().getSimpleName());
		
		failureRepo.delete(failure);
		
	}

	

}
