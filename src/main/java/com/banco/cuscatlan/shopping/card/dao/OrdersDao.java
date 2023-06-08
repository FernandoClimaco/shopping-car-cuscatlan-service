package com.banco.cuscatlan.shopping.card.dao;

import java.util.List; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.banco.cuscatlan.shopping.card.models.Orders;
import com.banco.cuscatlan.shopping.card.models.User; 
 
@Repository
public interface OrdersDao extends CrudRepository<Orders, Integer>{
	
	@Query("SELECT e FROM Orders e WHERE e.userIduser = :user")
	List<Orders> getOrdersByUser(@Param("user") User user);
	
	@Query("SELECT e FROM Orders e WHERE e.userIduser = :user and e.idstatus.idstatus = :status ")
	List<Orders> getOrdersById(@Param("user") User user , @Param("status") Integer status);
	
	@Query("SELECT e FROM Orders e WHERE e.idorders = :idorders")
	List<Orders> getOrdersByIdorders(@Param("idorders") int idorders);
	
	

	
}
