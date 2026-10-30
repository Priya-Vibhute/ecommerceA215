package com.study.backend.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.study.backend.dtos.ProductDto;
import com.study.backend.entities.Product;
import com.study.backend.exceptions.IdNotFoundException;
import com.study.backend.repositories.ProductRepository;
import com.study.backend.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		
		// dto to entity
		Product product = modelMapper.map(productDto, Product.class);
		
		//save product to database
		Product savedProduct = productRepository.save( product);
		
		// entity to dto
		ProductDto savedDto = modelMapper.map(savedProduct, ProductDto.class);
		
		return savedDto;
	}

	@Override
	public Page<ProductDto> getAllProducts(int page,int size) {
		
		// findAll returns list of products from database
		
		PageRequest pageRequest = PageRequest.of(page, size);
		
		Page<Product> products = productRepository.findAll(pageRequest);
		
		Page<ProductDto> dtoList = products.map(p->modelMapper.map(p, ProductDto.class));
		
	
		return dtoList;
	}

	@Override
	public ProductDto getProducById(Integer id) {
		
		Product product = productRepository.findById(id)
		.orElseThrow(()->new IdNotFoundException
				("Product with given Id not found"));
		
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		
		return productDto;
	}

	@Override
	public void deleteProduct(Integer id) {
		
		
		Product product = productRepository.findById(id)
				.orElseThrow(()->new IdNotFoundException
						("Product with given Id not found"));
		
		productRepository.delete(product);
					
	}

	@Override
	public ProductDto updateProduct(Integer id, ProductDto productDto) {
		
		Product product = productRepository.findById(id)
				.orElseThrow(()->new IdNotFoundException
						("Product with given Id not found"));
		
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImageUrl(productDto.getImageUrl());

				
		Product updatedProduct = productRepository.save(product);
		
		ProductDto dto = modelMapper.map(updatedProduct, ProductDto.class);
		
		return dto;
	}

}
