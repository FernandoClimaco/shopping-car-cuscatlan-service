package com.banco.cuscatlan.shopping.card.service;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banco.cuscatlan.shopping.card.dao.OrdersDao;
import com.banco.cuscatlan.shopping.card.dao.PaymentDao;
import com.banco.cuscatlan.shopping.card.dao.ShoppingDetailDao;
import com.banco.cuscatlan.shopping.card.models.AmountPaidTransaction;
import com.banco.cuscatlan.shopping.card.models.Orders;
import com.banco.cuscatlan.shopping.card.models.Payment;
import com.banco.cuscatlan.shopping.card.models.PaymentRequestTransaction;
import com.banco.cuscatlan.shopping.card.models.ResponseTransaction;
import com.banco.cuscatlan.shopping.card.models.User;
import com.banco.cuscatlan.shopping.card.util.OrdersStatusConstans;

@Service
public class PaymentService {

	
	Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private AutenticationService autenticationService;

	@Autowired
	private ShoppingDetailService detailService;

	@Autowired
	private ShoppingDetailDao shoppingDetailDao;

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private PaymentDao paymentDao;


	public ResponseEntity<?> doPaymentOrder(String token, PaymentRequestTransaction paymentRequest) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Double amountPaidTransaction = detailService.getAmountPaidTransaction(paymentRequest.getIdOrder(), user);
			if (amountPaidTransaction == null) {
				return new ResponseEntity<ResponseTransaction>(
						new ResponseTransaction("400", "Invalid order id ".concat(paymentRequest.getIdOrder().toString())),
						HttpStatus.BAD_REQUEST);
			}
			if (amountPaidTransaction.intValue() == paymentRequest.getAmountPaid().intValue()) {
				Integer updateToPaid = shoppingDetailDao.updateToPaid(paymentRequest.getIdOrder());
				if (updateToPaid > 0) {
					Payment payment = new Payment();
					payment.setCreated(new Date()); 
					payment.setOderid(paymentRequest.getIdOrder());
					payment.setTotal(amountPaidTransaction);
					payment.setDescription("payment has been made for the amount of " + amountPaidTransaction + " the order id "
							+ paymentRequest.getIdOrder() + " date: " + payment.getCreated());
					payment.setUser(user.getIduser());
					paymentDao.save(payment);
					ordersDao.save(new Orders(paymentRequest.getIdOrder(), OrdersStatusConstans.COMPLETED, user));
					return ResponseEntity.ok(payment);
				}
				return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("404", "no record was updated"),
						HttpStatus.NOT_ACCEPTABLE);
			} else {
				return new ResponseEntity<ResponseTransaction>(
						new ResponseTransaction("400", "the correct amount to be paid is $".concat(amountPaidTransaction.toString())),
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	 

	
	public ResponseEntity<?> getPaymentsForOrder(String token) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			List<Payment> listPayments = paymentDao.getPayments(user.getIduser());
			if (listPayments != null && listPayments.size() > 0) {
				return ResponseEntity.ok(listPayments);
			} else {
				return new ResponseEntity<ResponseTransaction>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	public ResponseEntity<?> getAmountPayForOrder(String token, Integer idOrder) {
		try {
			User user = autenticationService.informationByToken(token);
			if (user == null) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			Double amountPaidTransaction = detailService.getAmountPaidTransaction(idOrder, user);
			if (amountPaidTransaction == null) {
				return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("400", "Incorrect order id ".concat(idOrder.toString())),
						HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(new AmountPaidTransaction(amountPaidTransaction));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ResponseTransaction>(new ResponseTransaction("500", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
