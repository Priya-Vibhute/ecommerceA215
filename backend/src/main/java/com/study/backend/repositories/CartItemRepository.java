package com.study.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.backend.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

}
