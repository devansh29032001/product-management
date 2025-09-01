package com.bitsandbites.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
	
	@NotBlank(message="name is required")
	private String name;
	private List<ProductDto>products;  // add product list
}

//Usually when creating a category, you don’t create products inside it — you only add products separately via ProductDto.
//So in most cases, products is optional → no validation needed.


//We add annotations like:
//
//@NotBlank → field must not be null/empty.
//
//@NotNull → field must not be null.
//
//@Positive → must be > 0.
//
//@Size(min=, max=) → string length restrictions.