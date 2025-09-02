package com.bitsandbites.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitsandbites.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	// filtering and query methods
	List<Product> findByCategoryName(String name);
	
	List<Product> findByPriceBetween(double min,double max);
	
	
	//	here we are using a jpql query using @Query.If we want to use sql then we need to pass nativequery=true;
	@Query("Select p from Product p WHERE p.price>:price")
	List<Product> findProductCostlierThan(@Param  ("price") double price);
	
	
	//for native sql 
//	@Query(value = "SELECT * FROM products WHERE price BETWEEN :min AND :max", nativeQuery = true)
//	List<Product> findProductsInRange(@Param("min") double min, @Param("max") double max);

	
}

//In Spring Data JPA, whenever you define a custom query method, it must start with one of the reserved prefixes like:
//findBy…
//readBy…
//queryBy…
//getBy…
//countBy…
//existsBy…
//deleteBy…
//👉 Most common (and recommended) is findBy.


//@Query
//whenever we want full control over the sql/jpql quries then we use @Query