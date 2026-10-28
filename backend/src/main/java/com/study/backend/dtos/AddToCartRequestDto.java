package com.study.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequestDto {
	
	private String userId;
	private Integer productId;
	private Integer quantity;

}
