package com.techland.paypay.user.impl;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.LoginCommand;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.responses.ServiceResponse;
import com.techland.paypay.user.users.GeneralUser;
import com.techland.paypay.user.users.UserFactory;
import com.techland.paypay.user.users.UserTypes;
import com.techland.paypay.user.util.LogFeed;

@RestController
public class PayPayUserController {

	private ServiceResponse resp;
	private GeneralUser user;
	private UserEntity userEntity;
	private LogFeed logfeed;
	


	public PayPayUserController(ServiceResponse resp, GeneralUser user,UserEntity userEntity,LogFeed logfeed) {
		this.resp = resp;
		this.user = user;
		this.userEntity = userEntity;
		this.logfeed = logfeed;
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
			
			logfeed.getInstance(Constants.SUCESS_MESSAGE,PayPayUserController.class,user.toString()).process();
		

		} catch (Exception e) {
		
			resp.setMessaged(Constants.SERVER_ERROR);
			resp.setResponseCode(Constants.SERVER_ERROR_CODE);
			resp.setSuccess(false);
			
			logfeed.getInstance(Constants.SERVER_ERROR,PayPayUserController.class,user.toString(),e.getMessage()).process();
		
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
