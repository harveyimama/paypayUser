package com.techland.paypay.user.contracts;

import com.techland.paypay.user.impl.AddUserCommand;
import com.techland.paypay.user.impl.UserState;

public interface User {
	
	//read
	default  UserState login(String username,String password)
	{
		return null;
	}
	
	default UserState getAccountdetails(String id)
	{
		return null;
	}
	
	//write
	default void changePassword(String oldPassowrd,String newPassword)
	{}
	
	default void verifyEmail(String id)
	{}
	
	void openAccount(AddUserCommand user,String eventId);

	void updateAccount();
	
	void updateAccountStatus();

}
