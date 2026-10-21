package com.study.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	{
		httpSecurity
		
		.csrf(csrf->csrf.disable())
		
		.authorizeHttpRequests(request->
		          
		          request.requestMatchers(HttpMethod.GET,"/products/**")
		         .permitAll()
		         .requestMatchers(HttpMethod.POST,"/users")
		         .permitAll()
		         
		          
		)
		.httpBasic(Customizer.withDefaults());
		
		
		httpSecurity.exceptionHandling(exception->exception
				.authenticationEntryPoint(authenticationEntryPoint));
		
		return httpSecurity.build();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
