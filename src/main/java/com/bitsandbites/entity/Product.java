package com.bitsandbites.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity

public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	
	
}


//Infinite Recursion Problem
//Since you’re already using DTOs (ProductDto, CategoryDto) →
//👉 You won’t face recursion if you only return DTOs in controllers (because DTOs break cycles naturally).
//
//But if you expose entities in responses (for testing or Swagger demo), you’ll definitely need 
//@JsonManagedReference / @JsonBackReference.


//If you now expose your entities directly (instead of DTOs), Spring (via Jackson) will try to serialize:
//Category → Products → Category → Products → Category … → Infinite Loop (StackOverflowError)
//✅ Solution (to fix infinite recursion)
//
//Add @JsonManagedReference on the parent side (Category.products) and @JsonBackReference on the child side (Product.category).
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//@Entity
//public class Category {
//    ...
//    @OneToMany(mappedBy="category", cascade=CascadeType.ALL, orphanRemoval=true)
//    @JsonManagedReference
//    List<Product> products = new ArrayList<>();
//}
//
//@Entity
//public class Product {
//    ...
//    @ManyToOne
//    @JoinColumn(name="category_id")
//    @JsonBackReference
//    private Category category;
//}
//
//🔹 What Happens Now?
//When you fetch a Category → JSON will include its products but won’t recursively include their category.
//When you fetch a Product → JSON will show product fields but skip the category (since it’s the back reference).