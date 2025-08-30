package com.bitsandbites.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitsandbites.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
