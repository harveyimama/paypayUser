package com.techland.paypay.user.contracts;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.StatusChangeCommand;
import com.techland.paypay.user.helper.Status;
import com.techland.paypay.user.impl.UserEntity;
import com.techland.paypay.user.impl.UserState;

public interface User {
	
	//read
	default  com.techland.paypay.user.persistence.User login(String username,String password,UserEntity userEntity)
	{
		return userEntity.login(username, password);
	}
	
	default UserState getAccountdetails(String id,UserEntity userEntity)
	{
		return userEntity.getState(id);
		
	}
	
	//write
	default void changePassword(String oldPassowrd,String newPassword)
	{}
	
	default void verifyEmail(String id)
	{}
	
	void openAccount(AddUserCommand user);

	void updateAccount();
	
	void updateAccountStatus(StatusChangeCommand statusCommand );

}
