package com.bitsandbites.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitsandbites.entity.Category;
import com.bitsandbites.entity.Product;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
	
}
