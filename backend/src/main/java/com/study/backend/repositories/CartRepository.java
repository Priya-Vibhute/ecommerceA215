package com.study.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.backend.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
 