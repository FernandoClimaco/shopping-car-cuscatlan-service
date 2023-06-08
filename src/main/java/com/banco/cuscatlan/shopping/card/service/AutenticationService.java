package com.banco.cuscatlan.shopping.card.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.banco.cuscatlan.shopping.card.models.User;
import org.apache.commons.lang3.StringUtils;


@Service
public class AutenticationService {
	
	Logger logger = LoggerFactory.getLogger(AutenticationService.class);
	
	private final static String HEADER_TOKEN="Authorization";
	
	@Value("${device.security.cuscatlan.service.url}")
	private String baseUrl;
	
	@Value("${device.security.cuscatlan.service.auth.validate.endpoint}")
	private String authValidateEndpoint;
	
	
	@Autowired
	private RestTemplate restTemplate;
	

	public User informationByToken(String token){
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set(HEADER_TOKEN, token);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			final String url = StringUtils.join(this.baseUrl, this.authValidateEndpoint);
			ResponseEntity<User> user = restTemplate.postForEntity(url, request,  User.class);
			if(user.getBody() !=null) return user.getBody();
			if(user.getBody() ==null) logger.debug("The body is null: " + user);
			
		}catch (HttpStatusCodeException h) {
			logger.error(h.getMessage()); 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;	
	}
	
}
