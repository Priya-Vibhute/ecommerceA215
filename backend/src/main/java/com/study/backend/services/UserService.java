package com.study.backend.services;

import com.study.backend.dtos.UserRequestDto;
import com.study.backend.dtos.UserResponseDto;

public interface UserService {
	
	
	UserResponseDto register(UserRequestDto userRequestDto);
	
	

}
