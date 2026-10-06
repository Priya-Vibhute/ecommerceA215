package com.study.backend.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.backend.dtos.UserRequestDto;
import com.study.backend.dtos.UserResponseDto;
import com.study.backend.entities.User;
import com.study.backend.enums.Role;
import com.study.backend.repositories.UserRepository;
import com.study.backend.services.UserService;


@Service  // creates bean as well as marks class as buisness logic layer
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserResponseDto register(UserRequestDto userRequestDto) {
		
		// dto to entity conversion
		User user = modelMapper.map(userRequestDto, User.class);
		
		// set role for registered user
		user.setRole(Role.ROLE_USER);
		
		
       //save entity object to database 
		User savedUser = userRepository.save(user);
		
		
		//  entity to dto
		UserResponseDto savedDto = modelMapper.map(savedUser, UserResponseDto.class);
		

		
		return savedDto;
	}

}
