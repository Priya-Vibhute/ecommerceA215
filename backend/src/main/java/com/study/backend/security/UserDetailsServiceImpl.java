package com.study.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.study.backend.entities.User;
import com.study.backend.exceptions.EmailNotFoundException;
import com.study.backend.repositories.UserRepository;

public class UserDetailsServiceImpl  implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email)
		.orElseThrow(()->new EmailNotFoundException("User with given email not found"));	
		
		return user;
	}

}
