package com.bitsandbites.dto;

import java.util.List;



import lombok.Data;

@Data
public class CategoryDto {
	private String name;
	private List<ProductDto>products;  // add product list
}
