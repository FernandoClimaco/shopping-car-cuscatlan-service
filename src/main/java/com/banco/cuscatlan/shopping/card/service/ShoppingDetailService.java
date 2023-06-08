package com.banco.cuscatlan.shopping.card.service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.banco.cuscatlan.shopping.card.dao.ProductDao;
import com.banco.cuscatlan.shopping.card.dao.ShoppingDetailDao;
import com.banco.cuscatlan.shopping.card.models.Orders;
import com.banco.cuscatlan.shopping.card.models.Product;
import com.banco.cuscatlan.shopping.card.models.ResponseTransaction;
import com.banco.cuscatlan.shopping.card.models.ShoppingDetail; 
import com.banco.cuscatlan.shopping.card.models.User;


@Service
public class ShoppingDetailService {

	Logger logger = LoggerFactory.getLogger(ShoppingDetailService.class);

	@Autowired
	private ProductDao ProductDao;
	
	@Autowired
	private ShoppingDetailDao shoppingDetailDao;

	@Autowired
	private AutenticationService autenticationService;

	

	 
	public Boolean generatoDetailOfNowOrder(List<Product> product, Orders order) {
		try {
			List<ShoppingDetail> shoppingDetailOrder = shoppingDetailNow(product, order);
			if (shoppingDetailOrder != null) {
				List<ShoppingDetail> shoppingDetail = (List<ShoppingDetail>) shoppingDetailDao.saveAll(shoppingDetailOrder);
				if (shoppingDetail != null && shoppingDetail.size() > 0) {
					try {
						ProductDao.deleteAll(product);
						return Boolean.TRUE;
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Boolean.FALSE;
	}

	
	
	public ResponseEntity<?> getDetailByOrderNow(User user, Integer idOrder) {
		try {
			List<ShoppingDetail> listDetail = shoppingDetailDao.getDatailByOrder(idOrder, user);
			if (listDetail != null && listDetail.size() > 0) {
				return new ResponseEntity<List<ShoppingDetail>>(listDetail, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	public Double getAmountPaidTransaction(Integer orderTransaction, User user) {
		try {
			Double amountShopping = shoppingDetailDao.getAmountPaid(orderTransaction, user);
			return amountShopping != null && amountShopping > 0 ? amountShopping : null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	
	
	public ResponseEntity<?> detailsOrden(String token) {
		try {
			User user =  autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<ShoppingDetail> list =  (List<ShoppingDetail>) shoppingDetailDao.findAll();
			if (list != null && list.size() > 0) {
				return new ResponseEntity<List<ShoppingDetail>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	public ResponseEntity<?> removeProductOnDetail(String token, Integer idOrder) {
		try {
			User user =  autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Integer rowRemove = shoppingDetailDao.removeProductOnDetail(user.getIduser(), idOrder);
			return new ResponseEntity<ResponseTransaction>(
					new ResponseTransaction("200", String.format("Shoppingdetails removed has been : %s", rowRemove), rowRemove),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	private List<ShoppingDetail> shoppingDetailNow(List<Product> product, Orders order) {
		List<ShoppingDetail> finalDetails = null;
		if (product != null && product.size() > 0 && order != null) {
			finalDetails = product.stream().map(pdetails -> {
				ShoppingDetail detail = new ShoppingDetail();
				detail.setImage(pdetails.getImage());
				detail.setPaid(Boolean.FALSE);
				detail.setPrice(pdetails.getPrice());
				detail.setProductid(pdetails.getIdapi());
				detail.setTitle(pdetails.getTitle());
				detail.setIdorders(order);
				return detail;
			}).collect(Collectors.toList());
		}
		return finalDetails;
	}
}
