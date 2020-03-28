package com.techland.paypay.user.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techland.paypay.user.impl.UserState;

@Service
public class Processor {
	@Autowired
	private UserRepository userRepo;
	
	
	public User login(String username, String password) {
		return  userRepo.findByUsernameAndPassword(username, password);
	}

	public UserState getAccountdetails(String id) {
		
		User user = userRepo.findById(id).get();
		UserState u = new UserState();
		u.setEmail(user.getEmail());
		u.setFullname(user.getFullname());
		u.setId(id);
		u.setPassword(user.getPassword());
		u.setRole(user.getRole());
		u.setStatus(user.getStatus());
		u.setUsername(user.getUsername());
		u.setUserType(user.getUserType());
		
		return u;
	}
	
public boolean existsByemail(String email) {
		
	return  userRepo.existsByEmail(email);

	}



}
