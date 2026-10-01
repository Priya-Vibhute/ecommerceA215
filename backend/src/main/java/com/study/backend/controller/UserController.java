package com.study.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.backend.dtos.UserRequestDto;
import com.study.backend.dtos.UserResponseDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	// ==========================================================
	// POST - localhost:8080/users
	//============================================================
	
	@PostMapping
	public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto)
	{
		System.out.println(userRequestDto);
		
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setFirstName(userRequestDto.getFirstName());
		userResponseDto.setLastName(userRequestDto.getLastName());
		userResponseDto.setEmail(userRequestDto.getEmail());
		
		return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.CREATED);
	}

}
