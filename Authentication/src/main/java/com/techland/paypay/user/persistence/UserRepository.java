package com.techland.paypay.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>  {

	User findByUsernameAndPassword(String username, String password);

	boolean existsByEmail(String email);


}
