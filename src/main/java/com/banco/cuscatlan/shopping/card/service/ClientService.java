package com.banco.cuscatlan.shopping.card.service;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banco.cuscatlan.shopping.card.dao.UserRepository;
import com.banco.cuscatlan.shopping.card.models.ResponseTransaction;
import com.banco.cuscatlan.shopping.card.models.User;


@Service
public class ClientService {

	Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private AutenticationService autenticationService;
	
	@Autowired
	private UserRepository userRepository;
	

	public ResponseEntity<?> getExtractOneInformation(String bearerToken){
		try {
			User user = autenticationService.informationByToken(bearerToken);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			user.setPassword(null);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	
	public ResponseEntity<?> getListClients(String bearerToken,Pageable pageable){
		try {
			User user = autenticationService.informationByToken(bearerToken);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			 Page<User> listUsers =  userRepository.findAll(pageable);
			 
			 if(listUsers != null && listUsers.getContent() !=null) {
					listUsers.forEach(usr -> usr.setPassword(""));
					return new ResponseEntity<Page<User>>(listUsers , HttpStatus.OK);
				}
			 return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			 
			 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
