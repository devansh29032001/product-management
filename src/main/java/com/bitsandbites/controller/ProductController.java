package com.bitsandbites.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitsandbites.dto.ProductDto;
import com.bitsandbites.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
		name="Product Service REST API CRUD operation",
		description="CREATE READ UPDATE DELETE operations for Product REST API"
	)//swagger annotation to provide name and description of the controller class
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
	}
	
	
	@Operation(
			summary="Fetch all products",
			description="REST API to fetch all products"
	)//to provide description of controller methods


	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
	}
	
//	implementing pagination in controller
//	@GetMapping
//	public ResponseEntity<Page<ProductDto>> getAllProduct( @PageableDefault(size = 5, sort = "name") Pageable pageable){
//		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct(pageable));
//	}
//	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
	}	
	
	//filter and query methods
	@GetMapping("/category-name/{name}")
	public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String name){
		return ResponseEntity.status(HttpStatus.OK).body(productService.findProductsByCategory(name));
	}
	
	@GetMapping("costlier-than/{price}")
	public ResponseEntity<List<ProductDto>> getProductsCostlierThan(@PathVariable double price){
		return ResponseEntity.status(HttpStatus.OK).body(productService.findProductsCostlierThan(price));
	}
	
	@GetMapping("/by-price")
	public ResponseEntity<List<ProductDto>> getProductsBetweenPrice(@RequestParam double min,@RequestParam double max){
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProductBetweenPrice(min, max));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductDto productDto){
		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}



