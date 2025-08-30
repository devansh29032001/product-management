package com.bitsandbites.mapper;

import com.bitsandbites.dto.ProductDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.entity.Product;

public class ProductMapper {
	public static ProductDto entityToDto(Product product) {
		//first we create a Dto object
		ProductDto productDto=new ProductDto();
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		
		if(product.getCategory()!=null) {
			productDto.setCategoryId(product.getCategory().getId());
		}
		
		return productDto;
	}
	
	public static Product dtoToEntity(ProductDto productDto,Category category) {
		Product product =new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setCategory(category);
		return product;
	}
}	
