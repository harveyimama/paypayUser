package com.techland.paypay.user.impl;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.user.config.UserRepository;
import com.techland.paypay.user.contracts.InteractiveQueryService;
import com.techland.paypay.user.contracts.UserType;
import com.techland.paypay.user.entity.User;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.response.ServiceResponse;
import com.techland.paypay.user.usertypes.UserTypeFactory;
import com.techland.paypay.user.usertypes.UserTypes;

@RestController
public class PayPayUserController {

	private ServiceResponse resp;

	public PayPayUserController(ServiceResponse resp) {
		this.resp = resp;

	}

	@PostMapping(path = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse CreateUser(@RequestBody final UserCommand user) {
		private InteractiveQueryService interactiveQueryService; 
		try {

			UserType userType = UserTypeFactory.getInstance(UserTypes.valueOf(user.getUserType()));
			userType.openAccount(user);

			resp.setMessaged(Constants.SUCESS_MESSAGE);
			resp.setResponseCode(Constants.SUCESS_CODE);
			resp.setSuccess(true);

		} catch (Exception e) {
			resp.setMessaged(Constants.SERVER_ERROR);
			resp.setResponseCode(Constants.SERVER_ERROR_CODE);
			resp.setSuccess(false);
		}
		return resp;
	}
	
	@PostMapping(path = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse getUserById(@Param(value = "id") final String id) {
		
		try {

			UserType userType = UserTypeFactory.getInstance(UserTypes.valueOf(user.getUserType()));
			userType.getAccountdetails(id);

			resp.setMessaged(Constants.SUCESS_MESSAGE);
			resp.setResponseCode(Constants.SUCESS_CODE);
			resp.setSuccess(true);

		} catch (Exception e) {
			resp.setMessaged(Constants.SERVER_ERROR);
			resp.setResponseCode(Constants.SERVER_ERROR_CODE);
			resp.setSuccess(false);
		}
		return resp;
	}
}
