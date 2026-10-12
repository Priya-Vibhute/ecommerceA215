package com.study.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.backend.entities.Category;
import com.study.backend.entities.Product;
import com.study.backend.exceptions.IdNotFoundException;
import com.study.backend.repositories.CategoryRepository;
import com.study.backend.repositories.ProductRepository;
import com.study.backend.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Category addProductToCategory(Integer productId, Integer categoryId) {
		
		Category category = categoryRepository.findById(categoryId)
		.orElseThrow(()->new IdNotFoundException("Category with given id not found"));
		
		Product product = productRepository.findById(productId)
		.orElseThrow(()->new IdNotFoundException("Product with given id not found"));
		
		product.setCategory(category);
		
		productRepository.save(product);	
		
		return category;
	}

	@Override
	public List<Product> getProducts(Integer categoryId) {
		
		return null;
	}
	
	

}
