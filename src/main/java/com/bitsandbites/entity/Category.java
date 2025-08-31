package com.bitsandbites.entity;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Schema(
		name="Category",
		description="It holds category information along with products"
		)//swagger annotation to name and describe Schemas
@Data
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String name;
	
	@OneToMany(mappedBy="category" ,cascade=CascadeType.ALL,orphanRemoval=true)
	List<Product> products=new ArrayList<>();
}
