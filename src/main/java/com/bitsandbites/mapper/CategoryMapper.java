package com.bitsandbites.mapper;

import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.entity.Category;

public class CategoryMapper {
	public static CategoryDto entityToDto(Category category) {
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setName(category.getName());
		
		//we also want all the products to be listed along with there category 
		if(category.getProducts()!=null) {
			categoryDto.setProducts(category.getProducts().stream().map(ProductMapper::entityToDto).toList());
		}
		return categoryDto;
	}
	
	public static Category dtoToEntity(CategoryDto categoryDto) {
		Category category=new Category();
		category.setName(categoryDto.getName());
		  // we don’t set products here to avoid circular mapping
        return category;
	}
}
