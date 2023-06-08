package com.banco.cuscatlan.shopping.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.cuscatlan.shopping.card.service.ShoppingDetailService; 

@RestController
@RequestMapping("/details")
public class ShoppingDetailController {
	
	@Autowired
	private ShoppingDetailService shoppingDetailService;
	
	@GetMapping
	public ResponseEntity<?> detailsOrden(@RequestHeader("Authorization") String bearerToken) {
		return shoppingDetailService.detailsOrden(bearerToken );
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> getRemoveProducts(@PathVariable("id") Integer id ,@RequestHeader("Authorization") String bearerToken) {
		return shoppingDetailService.removeProductOnDetail(bearerToken , id);
	}
}
