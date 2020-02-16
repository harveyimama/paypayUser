package com.techland.paypay.user.config;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.techland.paypay.user.persistence.User;

public interface UserRepository extends ReactiveCrudRepository<User,String>  {

}
