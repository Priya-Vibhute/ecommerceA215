package com.study.backend.services;

import com.study.backend.dtos.AddToCartRequestDto;
import com.study.backend.entities.Cart;

public interface CartService {
	
	
	 void addToCart(AddToCartRequestDto addToCartRequestDto);
	 
	 Cart getCart(String userId);
	  
	 

}
