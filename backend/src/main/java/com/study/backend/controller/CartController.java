package com.study.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.backend.dtos.AddToCartRequestDto;
import com.study.backend.dtos.ApiResponse;
import com.study.backend.entities.User;
import com.study.backend.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody 
			AddToCartRequestDto addToCartRequestDto,@AuthenticationPrincipal User user)
	{
		
		addToCartRequestDto.setUserId(user.getId());
		
		cartService.addToCart(addToCartRequestDto);
		
		return ResponseEntity.ok(new ApiResponse("Product added successfully to cart"));
	}

}
