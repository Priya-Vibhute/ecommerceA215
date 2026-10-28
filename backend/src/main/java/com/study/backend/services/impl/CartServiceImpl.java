package com.study.backend.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.backend.dtos.AddToCartRequestDto;
import com.study.backend.entities.Cart;
import com.study.backend.entities.CartItem;
import com.study.backend.entities.Product;
import com.study.backend.entities.User;
import com.study.backend.exceptions.IdNotFoundException;
import com.study.backend.repositories.CartItemRepository;
import com.study.backend.repositories.CartRepository;
import com.study.backend.repositories.ProductRepository;
import com.study.backend.repositories.UserRepository;
import com.study.backend.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	

	@Override
	public void addToCart(AddToCartRequestDto addToCartRequestDto) {
		
		Cart cart = getCart(addToCartRequestDto.getUserId());
		
		Integer productId = addToCartRequestDto.getProductId();
		
		Product product = productRepository.findById(productId)
		.orElseThrow(()->new IdNotFoundException("Product with given id not found"));
		
		
		for(CartItem item :cart.getCartItems())
		{
			if(item.getProduct().getId()==productId)
			{
				item.setQuantity(item.getQuantity()+addToCartRequestDto.getQuantity());
				cartItemRepository.save(item);
				return ;
			}
		}
		
		
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(addToCartRequestDto.getQuantity());
		cartItem.setCart(cart);
		
		cartItemRepository.save(cartItem);

	}

	@Override
	public Cart getCart(String userId) {
		
		User user = userRepository.findById(userId)
		.orElseThrow(()->new IdNotFoundException("User with given id not found"));
		
		Cart cart = user.getCart();
		
		if(cart==null)
		{
			cart = new Cart();
			cart.setCartItems(new ArrayList<CartItem>());
			cart.setUser(user);
			
			cart = cartRepository.save(cart);
			
		}
		
		return cart;
	}
	
	

}
