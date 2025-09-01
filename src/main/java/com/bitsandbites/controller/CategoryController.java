package com.bitsandbites.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bitsandbites.dto.CategoryDto;
import com.bitsandbites.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catergoryDto){ 
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoryService.createCategory(catergoryDto));
	}
	
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id ,@Valid @RequestBody CategoryDto categoryDto){
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, categoryDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id){
		//return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
		//in standard REST practice, a DELETE request usually does not have a response body.
		
		  categoryService.deleteCategory(id);
		  return ResponseEntity.status(HttpStatus.OK).build();
		  
		  //.build() → finishes building the ResponseEntity with no body. 
	
	}

}

//we put @Valid in controller where we have a request body
//✅ In short:

//@Valid @RequestBody → validates entire objects (DTOs).
//Directly use constraints (@Positive, @NotNull) → for simple values like @PathVariable or @RequestParam.

//🔹 @RequestBody vs @PathVariable

//@RequestBody
//Binds a whole JSON object from the request body → DTO.
//DTO may have multiple fields, nested objects, lists, etc.
//That’s why we use @Valid → to check all constraints (@NotBlank, @Positive, etc.) on that DTO.

//@PathVariable
//Binds a single value (usually an ID) from the URL → e.g. /products/{id}.
//Example: /products/10 → id=10.
//Since it’s one primitive/simple value, you don’t need @Valid.
//Instead, you can directly annotate it:
//
//@GetMapping("/products/{id}")
//public ResponseEntity<ProductDto> getProduct(@PathVariable @Positive Long id) {
//  ...
//}
