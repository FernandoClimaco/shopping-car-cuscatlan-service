package com.banco.cuscatlan.shopping.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
import com.banco.cuscatlan.shopping.card.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientsController {

	@Autowired
	private ClientService service;
	
	@GetMapping("/extract-one-information")
	public ResponseEntity<?> getExtractOneInformation(@RequestHeader("Authorization") String bearerToken){
		return service.getExtractOneInformation(bearerToken);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllClientes(@RequestHeader("Authorization") String bearerToken, Pageable pageable){ 
		return service.getListClients(bearerToken, pageable);
	}
}
