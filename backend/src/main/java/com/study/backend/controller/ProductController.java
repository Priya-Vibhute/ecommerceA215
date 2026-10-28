package com.study.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.backend.dtos.ApiResponse;
import com.study.backend.dtos.ProductDto;
import com.study.backend.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	// ================================================================
	// POST : localhost:8080/products
	// ================================================================
	
	@PostMapping
	public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto)
	{
		
		ProductDto savedProduct = productService.addProduct(productDto);
		
		return new ResponseEntity<ProductDto>(savedProduct,HttpStatus.CREATED);
	}
	
	// ================================================================
	// GET : localhost:8080/products
	// ================================================================
	
	@GetMapping
	public ResponseEntity<Page<ProductDto>>
	getAllProducts(@RequestParam(defaultValue = "0") int page,
	               @RequestParam(defaultValue = "25") int size)
	{
		System.out.println(page+" "+size);
		Page<ProductDto> products = productService.getAllProducts(page,size);
		
		return ResponseEntity.ok(products);
	}
	
	
	
	// ===============================================================
	// GET : localhost:8080/products/{id}
	// ===============================================================
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById( @PathVariable Integer id )
	{
		ProductDto productDto = productService.getProducById(id);
		
		return ResponseEntity.ok(productDto);
	}
	
	
	// ===============================================================
	// PUT : localhost:8080/products/{id}
	// ===============================================================
	
	
	
	 @PutMapping("/{id}")
	 public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id 
			 ,@Valid @RequestBody ProductDto productDto )
	 {
		ProductDto updatedProduct = productService.updateProduct(id, productDto); 
		return ResponseEntity.ok(updatedProduct);
	 }
	 
	 
	
	// ===============================================================
	// DELETE : localhost:8080/products/{id}
	// ===============================================================
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id)
	 {
		 productService.deleteProduct(id);
		 
		 return ResponseEntity.ok(new ApiResponse("Product deleted succesfully"));
	 }
	
	
	 
	 

}
