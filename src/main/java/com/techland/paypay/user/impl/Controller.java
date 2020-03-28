package com.techland.paypay.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.user.commands.AddUserCommand;
import com.techland.paypay.user.commands.LoginCommand;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.helper.Settings;
import com.techland.paypay.user.persistence.Processor;
import com.techland.paypay.user.responses.ServiceResponse;
import com.techland.paypay.util.LogFeed;

@RestController
public class Controller {

	@Autowired
	private ServiceResponse resp;
	@Autowired
	private UserEntity userEntity;
	@Autowired
	private Processor processor;
	


	@PostMapping(path = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse CreateUser(@RequestBody final AddUserCommand user) {
		
		try {
					
		    userEntity.handleCommand(user);

			resp.setMessaged(Constants.SUCESS_MESSAGE);
			resp.setResponseCode(Constants.SUCESS_CODE);
			resp.setSuccess(true);
			LogFeed logfeed = new LogFeed.LogProcessor()  
					.setInfo(Constants.SUCESS_MESSAGE)
					.setClazz(Controller.class)
					.setDomain(Settings.DOMAIN)
					.setOtherInfo(user.toString())
					.build(); 
			logfeed.process();
		

		} catch (Exception e) {
		e.printStackTrace();
			resp.setMessaged(Constants.SERVER_ERROR);
			resp.setResponseCode(Constants.SERVER_ERROR_CODE);
			resp.setSuccess(false);
			LogFeed logfeed = new LogFeed.LogProcessor()  
					.setInfo(Constants.SERVER_ERROR)
					.setClazz(Controller.class)
					.setDomain(Settings.DOMAIN)
					.setOtherInfo(user.toString())
					.build(); 
			logfeed.process();
		
		}
		return resp;
	}
	
	@GetMapping(path = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserState getUserById(@PathVariable(value = "id") final String id) {
		 
		return  processor.getAccountdetails(id);	 

	}
	
	@GetMapping(path = "/api/user/email/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse getUserByEmail(@PathVariable(value = "id") final String id) {
		 
		if(processor.existsByemail(id))
		{
			resp.setMessaged(Constants.SUCESS_MESSAGE);
			resp.setResponseCode(Constants.SUCESS_CODE);
			resp.setSuccess(true);
		}
		else
		{
			resp.setMessaged(Constants.NOTFOUND_MESSAGE);
			resp.setResponseCode(Constants.NOTFOUND_CODE);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	@PostMapping(path = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public com.techland.paypay.user.persistence.User login(@RequestBody final LoginCommand login) {
		 
		return  processor.login(login.getUsername(), login.getPassword());

	}
	
}
