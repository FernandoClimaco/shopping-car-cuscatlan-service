package com.banco.cuscatlan.shopping.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.banco.cuscatlan.shopping.card.models.PaymentRequestTransaction;
import com.banco.cuscatlan.shopping.card.service.PaymentService; 

@RestController
@RequestMapping("payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	
	@GetMapping   
	public ResponseEntity<?> getPaymentsForOrder(@RequestHeader("Authorization") String bearerToken){
		return paymentService.getPaymentsForOrder(bearerToken);		
	}
	
	@GetMapping("/getAmountPayForOrder/{id}")   
	public ResponseEntity<?> getAmountPayForOrder(@RequestHeader("Authorization") String bearerToken ,@PathVariable("id") Integer idOrder ){
		return paymentService.getAmountPayForOrder(bearerToken , idOrder);		
	}
	
	@PostMapping("/generate")
	public ResponseEntity<?> generatePaymentOrder(@RequestHeader("Authorization") String bearerToken , @RequestBody PaymentRequestTransaction paymentRequest){	
		return paymentService.doPaymentOrder(bearerToken , paymentRequest);		 
	}
	
	
	
}
