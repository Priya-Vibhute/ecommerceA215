package com.study.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.backend.entities.User;

public interface UserRepository  extends JpaRepository<User, String>{
	
	//findByFieldName
	 Optional<User> findByEmail(String email);

}
