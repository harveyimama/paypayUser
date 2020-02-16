package com.techland.paypay.user.impl;

import java.util.UUID;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.contracts.User;
import com.techland.paypay.user.events.UserAddedEvent;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.response.ServiceResponse;
import com.techland.paypay.user.users.UserFactory;
import com.techland.paypay.user.users.UserTypes;

@RestController
public class PayPayUserController {

	private ServiceResponse resp;
	private InteractiveQueryService interactiveQueryService; 


	public PayPayUserController(ServiceResponse resp,InteractiveQueryService interactiveQueryService) {
		this.resp = resp;
		this.interactiveQueryService = interactiveQueryService;

	}

	@PostMapping(path = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceResponse CreateUser(@RequestBody final AddUserCommand user) {
		
		try {
			String id = UUID.randomUUID().toString();	
			User userType = UserFactory.getInstance(UserTypes.valueOf(user.getUserType()));
			userType.openAccount(user,id);

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
	public UserAddedEvent getUserById(@Param(value = "id") final String id) {
		
		KafkaStreams ks = null;
	
		
	    ReadOnlyKeyValueStore<String, Double> averageStore = ks
	            .store("user",
	                    QueryableStoreTypes.<String, Double>keyValueStore());
	    
	
		
		 final ReadOnlyKeyValueStore<String, UserAddedEvent> userStore = interactiveQueryService.getQueryableStore(Settings.STORE,
		 QueryableStoreTypes.<String, UserAddedEvent>keyValueStore());
		 
		 
		return userStore.get(id);
		 

	}
}
