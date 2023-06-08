package com.banco.cuscatlan.shopping.card.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.banco.cuscatlan.shopping.card.models.OrderStatus;
import com.banco.cuscatlan.shopping.card.dao.OrdersDao;
import com.banco.cuscatlan.shopping.card.dao.ProductDao;
import com.banco.cuscatlan.shopping.card.models.Orders;
import com.banco.cuscatlan.shopping.card.models.Product;
import com.banco.cuscatlan.shopping.card.models.ResponseTransaction;
import com.banco.cuscatlan.shopping.card.models.StatusUpdateRequest;
import com.banco.cuscatlan.shopping.card.models.User;
import com.banco.cuscatlan.shopping.card.util.OrdersStatusConstans;


@Service
public class OrdersService {

	Logger logger = LoggerFactory.getLogger(OrdersService.class);
	
	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private ProductDao productsCarDao;

	@Autowired
	private ShoppingDetailService shoppingDetailService;

	@Autowired
	private AutenticationService autenticationService;

	

	
	public ResponseEntity<?> getOrderbytoken(String token) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Orders> listOrders = ordersDao.getOrdersByUser(user);
			if (listOrders != null && listOrders.size() > 0) {
				return new ResponseEntity<List<Orders>>(listOrders, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Orders>>(listOrders,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	public ResponseEntity<?> getOrderDetailNow(String token, Integer id) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return shoppingDetailService.getDetailByOrderNow(user, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	
	public ResponseEntity<?> orderGenerate(String token) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Orders order = new Orders();
			order.setUserIduser(user);
			order.setIdstatus(OrdersStatusConstans.IN_PROGRESS);
			ordersDao.save(order);
			order.getUserIduser().setPassword(null);
			if (generateShoppingDetailOrder(user, order)) {
				return ResponseEntity.ok(order);
			} else {
				ordersDao.delete(order);
				return new ResponseEntity<ResponseTransaction>(
						new ResponseTransaction("400", "We could not found products on your shopping car."),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	public ResponseEntity<?> doRejectedStatus(String token) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			
			Orders order = new Orders();
			List<Orders> lstOrdenExtract = ordersDao.getOrdersByUser(user);
			 
			for(Orders extract : lstOrdenExtract) {
				order.setIdorders(extract.getIdorders());
				order.setUserIduser(user);
				order.setIdstatus(OrdersStatusConstans.REJECTED);
				ordersDao.save(order);
			} 
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	
	public ResponseEntity<?> getOrderstatusNow(String token, Integer id) {
		try {
			if (id == null || (id < 1 || id > 4)) {
				return new ResponseEntity<ResponseTransaction>(
						new ResponseTransaction("400 ", "the values ​​​​that you can enter to see the status of the order are shown , 1 = COMPLETE, 2 = IN PROCESS , 3 = CANCELED, 4= REJECTED"),
						HttpStatus.BAD_REQUEST);
			}
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Orders> listOrders = ordersDao.getOrdersById(user, id);
			if (listOrders != null && listOrders.size() > 0) {
				return new ResponseEntity<List<Orders>>(listOrders, HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseTransaction>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	
	private Boolean generateShoppingDetailOrder(User user, Orders order) {
		try {
			List<Product> listProducts = productsCarDao.getProductsByUser(user);
			return shoppingDetailService.generatoDetailOfNowOrder(listProducts, order);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Boolean.FALSE;
		}
	}
}
