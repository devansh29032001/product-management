package com.bitsandbites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

//for swagger documentation we give this annotation 
@OpenAPIDefinition(
			info=@Info(
					title="Product Service REST API documentation",
					description="Product service REST API",
					version="v1",
					contact=@Contact(
								name="Devansh Tripathi",
								email="devanshtrip999@gmail.com"
							)
				),
			externalDocs=@ExternalDocumentation(
						description="sharepoint url product service api",
						url="example.com"
					)
				
		)
	
@SpringBootApplication
public class ProductmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductmanagementApplication.class, args);
	}

}
