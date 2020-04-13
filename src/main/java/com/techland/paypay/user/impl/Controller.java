package com.techland.paypay.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techland.paypay.user.commands.LoginCommand;
import com.techland.paypay.user.persistence.Processor;

@RestController
public class Controller {

	@Autowired
	private Processor processor;

	@GetMapping(path = "/api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserState getUserById(@PathVariable(value = "id") final String id) {

		return processor.getAccountdetails(id);

	}

	@PostMapping(path = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public com.techland.paypay.user.persistence.User login(@RequestBody final LoginCommand login) {

		return processor.login(login.getUsername(), login.getPassword());

	}

}
