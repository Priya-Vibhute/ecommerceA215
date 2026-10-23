package com.study.backend.controller;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.backend.dtos.LoginRequestDto;
import com.study.backend.dtos.LoginResponseDto;
import com.study.backend.dtos.UserResponseDto;
import com.study.backend.entities.User;
import com.study.backend.security.jwt.JwtUtils;

@RestController
@RequestMapping("/auth")  
public class AuthController {
	
	 
    @Autowired
	private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private ModelMapper modelMapper;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> 
	            login(@RequestBody LoginRequestDto loginRequestDto)
	{
		String email = loginRequestDto.getEmail();
		String password = loginRequestDto.getPassword();
		
		
		Authentication authenticate=null;
		try
		{
		   authenticate = authenticationManager.authenticate
		          (new UsernamePasswordAuthenticationToken(email, password));
		   
		}catch(BadCredentialsException exception)
		{
			System.out.println("Credentials are incorrect");
			
		}
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
          User user   = (User)authenticate.getPrincipal();
          
          String token = jwtUtils.generateTokenFromUsername(user);
          
          
          LoginResponseDto loginResponseDto = new LoginResponseDto();
          loginResponseDto.setToken(token);
          
          UserResponseDto responseDto = modelMapper.map(user, UserResponseDto.class);
          loginResponseDto.setResponseDto(responseDto);
          

		  return ResponseEntity.ok(loginResponseDto);
	}

}
