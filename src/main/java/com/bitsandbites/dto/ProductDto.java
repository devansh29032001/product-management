package com.bitsandbites.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDto {
	
	@NotBlank(message="name is required")
	private String name;
	
	@Positive(message="should be greater than 0")
    private double price;
	
	@NotNull(message = "Category id is required")
    private Long categoryId;
}


