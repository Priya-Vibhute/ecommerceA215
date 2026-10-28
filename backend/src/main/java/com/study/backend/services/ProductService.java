package com.study.backend.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.study.backend.dtos.ProductDto;

public interface ProductService {
	
	// add  Product
	ProductDto addProduct(ProductDto productDto);
	
	// fetch all products
	Page<ProductDto>  getAllProducts(int page,int size);
	
	//fetch product by id
	ProductDto getProducById(Integer id);
	
	
	// delete product by  id
	void deleteProduct(Integer id);
	
	
	// update product based on id
	ProductDto updateProduct(Integer id,ProductDto productDto);
	
	

}
