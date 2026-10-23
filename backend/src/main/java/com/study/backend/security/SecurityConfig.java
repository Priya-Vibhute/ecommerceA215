package com.study.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.backend.security.jwt.AuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AuthFilter authFilter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	{
		httpSecurity
		
		.csrf(csrf->csrf.disable())
		
		.authorizeHttpRequests(request->
		          
		          request.requestMatchers(HttpMethod.GET,"/products/**")
		         .permitAll()
		         .requestMatchers(HttpMethod.POST,"/users","/auth/login")
		         .permitAll()
		                 
		)
		.httpBasic(Customizer.withDefaults());
		
		
		httpSecurity.exceptionHandling(exception->exception
				.authenticationEntryPoint(authenticationEntryPoint));
		
		httpSecurity.addFilterBefore
		(authFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		
		return httpSecurity.build();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager
	    (AuthenticationConfiguration authenticationConfiguration)
	{
		return authenticationConfiguration.getAuthenticationManager();
	}

}
