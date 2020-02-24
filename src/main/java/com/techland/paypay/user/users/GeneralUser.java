package com.techland.paypay.user.users;


import org.springframework.stereotype.Component;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.StatusChangeCommand;
import com.techland.paypay.user.contracts.User;

@Component
public final class GeneralUser implements User {

	@Override
	public String openAccount(AddUserCommand user) {
		return null;
		
	}

	@Override
	public void updateAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountStatus(StatusChangeCommand statusCommand) {
		// TODO Auto-generated method stub
		
	}

	
	

}
