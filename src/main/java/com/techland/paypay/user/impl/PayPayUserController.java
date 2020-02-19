package com.techland.paypay.user.impl;

import java.util.UUID;


import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.LoginCommand;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.responses.ServiceResponse;
import com.techland.paypay.user.users.UserFactory;
import com.techland.paypay.user.users.UserTypes;
import com.techland.paypay.user.util.LogFeed;
import com.techland.paypay.user.util.Logger;

@RestController
public class PayPayUserController {

	private ServiceResponse resp;
	private User user;
	private UserEntity userEntity;
	




	public PayPayUserController(ServiceResponse resp, User user,UserEntity userEntity) {
		this.resp = resp;
		this.user = user;
		this.userEntity = userEntity;
		}

	@PostMapping(path = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse CreateUser(@RequestBody final AddUserCommand user) {
		//String id = UUID.randomUUID().toString();	
		try {
		
			User userType = UserFactory.getInstance(UserTypes.valueOf(user.getUserType()));
			userType.openAccount(user);

			resp.setMessaged(Constants.SUCESS_MESSAGE);
			resp.setResponseCode(Constants.SUCESS_CODE);
			resp.setSuccess(true);
			
			Logger.process(new LogFeed(Constants.SUCESS_MESSAGE, this.getClass().getSimpleName(),
					UserAddedEvent.class.getSimpleName(), id, user.getId()));

		} catch (Exception e) {
		
			resp.setMessaged(Constants.SERVER_ERROR);
			resp.setResponseCode(Constants.SERVER_ERROR_CODE);
			resp.setSuccess(false);
			
			Logger.process(new LogFeed(Constants.SERVER_ERROR, this.getClass().getSimpleName(),
					UserAddedEvent.class.getSimpleName(), id, user.getId()));
		}
		return resp;
	}
	
	@PostMapping(path = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserState getUserById(@Param(value = "id") final String id) {
		 
		return  user.getAccountdetails(id,userEntity);	 

	}
	
	@PostMapping(path = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public com.techland.paypay.user.persistence.User login(@RequestBody final LoginCommand login) {
		 
		return  user.login(login.getUsername(), login.getPassword(), userEntity);

	}
	
}
