package com.bitsandbites.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsandbites.Exception.ResourceNotFoundException;
import com.bitsandbites.dto.ProductDto;
import com.bitsandbites.entity.Category;
import com.bitsandbites.entity.Product;
import com.bitsandbites.mapper.ProductMapper;
import com.bitsandbites.repository.CategoryRepository;
import com.bitsandbites.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public  ProductDto createProduct(ProductDto productDto) {
		Category category=categoryRepository.findById(productDto.getCategoryId())
				.orElseThrow(()->new RuntimeException("Category id not found "));
		Product product=ProductMapper.dtoToEntity(productDto, category);	
		Product saved=productRepository.save(product);
		return ProductMapper.entityToDto(saved);
		
	}
	
	public List<ProductDto> getAllProduct(){
		return productRepository.findAll().stream()
				.map(ProductMapper::entityToDto)
				.toList();
	}
	
	public ProductDto getProductById(Long id) {
		Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no specified product with id "+id));
		return ProductMapper.entityToDto(product);
	}
	
	
	public ProductDto updateProduct(Long id ,ProductDto productDto) {
		Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no category with id "+id));
		
		Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no product with id "+id));
		
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		
		Product updated=productRepository.save(product);
		
		return ProductMapper.entityToDto(updated);
		
	}
	
	public void deleteProduct(Long id) {
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("No product found with id "+ id);
		}
		productRepository.deleteById(id);
	}
	
}
