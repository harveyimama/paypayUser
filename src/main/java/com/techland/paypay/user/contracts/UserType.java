package com.techland.paypay.user.contracts;

import com.techland.paypay.user.impl.UserCommand;

public interface UserType {
	
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
	
	void openAccount(UserCommand user);

	void updateAccount();
	
	void updateAccountStatus();

}
