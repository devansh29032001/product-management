package com.bitsandbites.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsandbites.Exception.ResourceNotFoundException;
import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.mapper.CategoryMapper;
import com.bitsandbites.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=CategoryMapper.dtoToEntity(categoryDto);
		Category saved=categoryRepository.save(category);
		return CategoryMapper.entityToDto(saved);
	}
	
	public List<CategoryDto> getAllCategories(){
		return categoryRepository.findAll().stream()
				.map(CategoryMapper::entityToDto)
				.toList();
	}
	
	public CategoryDto getCategory(Long id) {
		return categoryRepository.findById(id)
				.map(CategoryMapper::entityToDto)
				.orElseThrow(()->new ResourceNotFoundException("no category found with id "+id));
		
	}
	
	//Updated
	public CategoryDto updateCategory(Long id,CategoryDto categoryDto) {
		Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No specified category found with id "+id));
		category.setName(categoryDto.getName());
		Category updated=categoryRepository.save(category);
		return CategoryMapper.entityToDto(updated);
	}
	
	//Deleted
	public void deleteCategory(Long id) {
		if(!categoryRepository.existsById(id)) {
			throw new ResourceNotFoundException("No category found with id "+id);
		}
		categoryRepository.deleteById(id);
	}
	
	
}
