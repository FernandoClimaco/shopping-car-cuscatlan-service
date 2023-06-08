package com.banco.cuscatlan.shopping.card.dao;

import org.springframework.data.repository.CrudRepository;
import com.banco.cuscatlan.shopping.card.models.OrderStatus;

public interface OrderStatusDao extends CrudRepository<OrderStatus, Integer> {

}
