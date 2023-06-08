package com.banco.cuscatlan.shopping.card.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.banco.cuscatlan.shopping.card.dao.ProductDao;
import com.banco.cuscatlan.shopping.card.models.Product;
import com.banco.cuscatlan.shopping.card.models.ResponseTransaction;
import com.banco.cuscatlan.shopping.card.models.User;



@Service
public class ProductsCarService {
	
	Logger logger = LoggerFactory.getLogger(ProductsCarService.class);
	
	@Value("${fake.store.api.url}")
	private String url;
	
	@Autowired
	private ProductDao productsCarDao;

	@Autowired
	private AutenticationService autenticationService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	 
	public  ResponseEntity<?> getProductsForToken(String token){
		try {
			User user = autenticationService.informationByToken(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Product> listProduct = productsCarDao.getProductsByUser(user);
			if(listProduct != null && listProduct.size() > 0) {
				listProduct.forEach(lProduct-> lProduct.getIduser().setPassword(""));
				return  new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
			}else {
				return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	public  ResponseEntity<?> getQuantityProducts(String token){
		try {
			User user = autenticationService.informationByToken(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Integer quantityProducts = productsCarDao.getCountOfProductsByUser(user);
			if(quantityProducts != null && quantityProducts.intValue()> 0) {
				return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("200" , String.format("It user have %s products on the car", quantityProducts) , quantityProducts) , HttpStatus.OK);
				
			}else {
				return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
	public ResponseEntity<?> saveOnProduct(Integer id , String token) {
		try {			
			User user = autenticationService.informationByToken(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}			
			String finalUrl = url.concat("products/").concat(id.toString());
			Product productResponse = restTemplate.getForObject(finalUrl, Product.class);
			if(productResponse == null) {
				return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("404" ,String.format("Product with id %s is not exist", id)) , HttpStatus.NOT_FOUND);
			}
			productsCarDao.save(mapperProduct(productResponse, user , id));
			logger.info("response: " +productResponse.toString());
			return new ResponseEntity<Product>( productResponse, HttpStatus.CREATED);
			
		}catch(HttpClientErrorException hc) {
			logger.error(hc.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" ,"Product don't found " + hc.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(HttpStatusCodeException h) {
			logger.error(h.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" ,"Product don't found " + h.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(ResourceAccessException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" ,"Product don't found " + ex.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	

	 

	
	public  ResponseEntity<?> getDeleteProducts(String token , Integer idProduct){
		try {
			User user = autenticationService.informationByToken(token);
			if(user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}		
			Integer productos = productsCarDao.deleteById(user , idProduct);
				return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("200" , String.format("Products removed has been : %s", productos) , productos) , HttpStatus.OK);		
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500" , e.getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	
	private Product mapperProduct(Product products , User user , Integer id) {
		Product product = new Product(); 
		product.setPrice(products.getPrice());
		product.setImage(products.getImage());
		product.setCategory(products.getCategory());
		product.setDescription(products.getDescription());
		product.setTitle(products.getTitle());
		product.setIdapi(id);
		product.setIduser(user);
		return product;
	}
}
