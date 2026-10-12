package com.study.backend.services;

import java.util.List;

import com.study.backend.entities.Category;
import com.study.backend.entities.Product;

public interface CategoryService {
	
	Category addProductToCategory(Integer productId,Integer categoryId);
	
	List<Product> getProducts(Integer categoryId);
	
	
	
	
}
