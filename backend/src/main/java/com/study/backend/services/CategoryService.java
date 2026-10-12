package com.study.backend.services;

import java.util.List;

import com.study.backend.dtos.ProductDto;
import com.study.backend.entities.Category;
import com.study.backend.entities.Product;

public interface CategoryService {
	
	Category addProductToCategory(Integer productId,Integer categoryId);
	
	List<ProductDto> getProducts(Integer categoryId);
	
	
	
	
}
