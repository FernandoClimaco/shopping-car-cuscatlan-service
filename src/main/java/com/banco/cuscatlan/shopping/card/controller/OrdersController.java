package com.banco.cuscatlan.shopping.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banco.cuscatlan.shopping.card.service.OrdersService; 

@RestController
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	

	@GetMapping("/orderstatus/{id}")
	public ResponseEntity<?> getOrderstatusNow(@RequestHeader("Authorization") String bearerToken , @PathVariable("id") Integer id){
		return ordersService.getOrderstatusNow(bearerToken, id);		 
	}
	
	
	@GetMapping("/details/{id}")
	public ResponseEntity<?> getOrderDetailNow(@RequestHeader("Authorization") String bearerToken , @PathVariable("id") Integer idOrder){
		return ordersService.getOrderDetailNow(bearerToken, idOrder);		 
	}
	
	
	@GetMapping("/orderbytoken")
	public ResponseEntity<?> orderbytoken(@RequestHeader("Authorization") String bearerToken){
		return ordersService.getOrderbytoken(bearerToken);		 
	}
	
	
	@PostMapping("/generate")
	public ResponseEntity<?> orderGenerate(@RequestHeader("Authorization") String bearerToken){
		return ordersService.orderGenerate(bearerToken);		 
	}
	
	
	@GetMapping("/convertrejected")
	public ResponseEntity<?> doRejectedStatus(@RequestHeader("Authorization") String bearerToken){
		return ordersService.doRejectedStatus(bearerToken);		 
	}
		
}
