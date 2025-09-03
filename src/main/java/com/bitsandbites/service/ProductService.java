package com.bitsandbites.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	
	//implementing loggers
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
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
		 logger.info("Fetching all products...");
		 
		 //default way to write getAllProduct Service method
//		return productRepository.findAll().stream()
//				.map(ProductMapper::entityToDto)
//				.toList();
		 
		 //this way we are implementing loggers
		 List<ProductDto> products=productRepository.findAll().stream()
				 .map(ProductMapper::entityToDto).toList();
		 logger.debug("Fetched {} products", products.size());
		 return products;
	}
	
	
//	To implement pagination
//	✅ Step 1: Update Service
//
//	Change your method from List to Page:
	
	
	public Page<ProductDto> getAllProduct(Pageable pageable){
		return productRepository.findAll(pageable).map(ProductMapper::entityToDto);
	}
//	the reason is that Page<T> in Spring Data JPA already has a built-in map() method that lets you convert its content (List<Entity>) into another type (List<Dto>) while keeping all the pagination metadata intact (like total pages, total elements, current page, etc.).
//
//	Example difference:
//	✅ Using Page.map() (recommended)
	
	
	//here we are using filter and query method which is defined in ProductRepository
	
	public List<ProductDto> findProductsByCategory(String categoryName){
		return productRepository.findByCategoryName(categoryName).stream()
				.map(ProductMapper::entityToDto).toList();
	}
	
	public List<ProductDto> findAllProductBetweenPrice(double min,double max){
		return productRepository.findByPriceBetween(min, max).stream()
				.map(ProductMapper::entityToDto).toList();
	}
	
	
	//finding product using @Query and native Sql
	public List<ProductDto> findProductsCostlierThan(double price){
		return productRepository.findProductCostlierThan(price).stream()
				.map(ProductMapper::entityToDto).toList();
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





//🔹 1. Why Pagination & Sorting?

//Suppose you have 10,000 products in your DB.
//If you call findAll(), Spring will return all 10,000 rows → heavy on DB + network.
//Instead, you can ask for one page at a time (say, 10 products per page).
//Sorting lets you order by name, price, date, etc.

//So Pagination = Efficient, Sorting = Organized.
//🔹 2. Pageable & Page in Spring Data JPA
//Spring Data JPA provides:
//Pageable → Object that tells:
//Which page number? (page=0, page=1, etc.)
//How many records per page? (size=10, 20, etc.)
//How to sort? (sort=name,asc or sort=price,desc)
//Page<T> → Wrapper that contains:
//getContent() → actual list of products
//getTotalPages() → number of pages
//getTotalElements() → total count
//isFirst(), isLast() → helpers





//Q). What is Logging (vs System.out.println)?
//
//System.out.println("msg") → goes only to console, can’t control format, can’t separate INFO/ERROR, can’t disable in production.
//
//Logging framework → configurable, supports different levels (info, warn, error…), can write to files, console, or monitoring tools (Splunk, ELK, etc.).
//
//Spring Boot uses:
//SLF4J (API - interface for logging)
//Logback (default implementation)

//2. Logging Levels
//Level	Usage
//TRACE	Very detailed, rarely used
//DEBUG	For debugging (values, flow)
//INFO	Normal flow (service started, request processed)
//WARN	Something unexpected, but not fatal
//ERROR	Failure, something broke

//private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


//write this in application.properties file
//logging configuration
//# Default level
//logging.level.root=INFO  
//
//# Show SQL queries from Hibernate
//logging.level.org.hibernate.SQL=DEBUG  
//logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE  
//
//# Enable DEBUG logs only for your package
//logging.level.com.bitsandbites=DEBUG  
//
//# Write logs to a file
//logging.file.name=logs/app.log

//writing loggers in controller
//@GetMapping
//public ResponseEntity<List<ProductDto>> getAllProducts() {
//    logger.info("GET request received for all products");
//    List<ProductDto> products = productService.getAllProducts();
//    logger.info("Returning {} products", products.size());
//    return ResponseEntity.ok(products);
//}


