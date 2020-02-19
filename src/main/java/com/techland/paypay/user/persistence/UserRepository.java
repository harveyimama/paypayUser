package com.techland.paypay.user.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User,String>  {

	User findByUsernameAndPassword(String username, String password);

}
