package com.techland.paypay.user.contracts;

import com.techland.paypay.user.impl.AddUserCommand;

public interface User {
	
	//read
	default  void login(String username,String password)
	{}
	
	default void getAccountdetails(String id)
	{
		
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
