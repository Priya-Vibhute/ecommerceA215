package com.study.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.backend.entities.Category;
import com.study.backend.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
//	  localhost:8080/category/1/products/2
	
	@PutMapping("/{categoryId}/products/{productId}")
	public ResponseEntity<Category> addProductToCategory(@PathVariable Integer categoryId,
			                                             @PathVariable Integer productId)
	{
		Category category = categoryService.addProductToCategory(productId, categoryId);
		
		return ResponseEntity.ok(category);
	}
	
	

}
