package com.banco.cuscatlan.shopping.card.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.cuscatlan.shopping.card.service.ProductsCarService; 

@RestController
@RequestMapping("products")
public class ProductsCarController {

	@Autowired
	private ProductsCarService productsCarService;
	
	
	@GetMapping
	public ResponseEntity<?> getProductsForToken(@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.getProductsForToken(bearerToken);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> saveOnProduct(@PathVariable("id") Integer id,
			@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.saveOnProduct(id, bearerToken);
	}
	
	@GetMapping("/quantity")
	public ResponseEntity<?> getCountProducts(@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.getQuantityProducts(bearerToken);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> getDeleteProducts(@PathVariable("id") Integer id ,@RequestHeader("Authorization") String bearerToken) {
		return productsCarService.getDeleteProducts(bearerToken , id);
	}
	
}
