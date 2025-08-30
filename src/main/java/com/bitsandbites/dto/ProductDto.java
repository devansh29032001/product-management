package com.bitsandbites.dto;

import lombok.Data;

@Data
public class ProductDto {
	private String name;
    private double price;
    private Long categoryId;
}
