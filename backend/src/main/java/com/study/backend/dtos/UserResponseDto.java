package com.study.backend.dtos;


import com.study.backend.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private Role role;
	
}
