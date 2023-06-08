package com.banco.cuscatlan.shopping.card.util;

import com.banco.cuscatlan.shopping.card.models.OrderStatus;


public class OrdersStatusConstans {

	public static final OrderStatus COMPLETED = new OrderStatus(1, "COMPLETED");
	
	public static final OrderStatus IN_PROGRESS = new OrderStatus(2, "IN PROGRESS");
	
	public static final OrderStatus CANCELED = new OrderStatus(3, "CANCELED");
	
	public static final OrderStatus REJECTED = new OrderStatus(4, "REJECTED");
	
}
