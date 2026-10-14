package com.study.backend.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
	@NotNull
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String password;
	
	private String confirmPassword;
}
