package com.banco.cuscatlan.shopping.card.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.banco.cuscatlan.shopping.card.models.ShoppingDetail;
import com.banco.cuscatlan.shopping.card.models.User;
import jakarta.transaction.Transactional;



@Repository
public interface ShoppingDetailDao extends CrudRepository<ShoppingDetail, Integer>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE ShoppingDetail e SET e.paid = TRUE WHERE e.idorders.idorders = :idOrder " )
	Integer updateToPaid(@Param("idOrder") Integer idOrder);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE s FROM shoppingdetail s INNER JOIN orders o ON o.idorders=s.idorders where o.user_iduser = :iduser and s.iddetail = :detail and s.paid = 0" , nativeQuery = true )
	Integer removeProductOnDetail(@Param("iduser") Integer iduser , @Param("detail") Integer detail);
	
	@Query("SELECT e FROM ShoppingDetail e WHERE e.idorders.idorders = :idorders and e.idorders.userIduser = :user")
	List<ShoppingDetail> getDatailByOrder(@Param("idorders") Integer idorders , @Param("user") User user);
	 
	@Query("SELECT SUM(e.price) FROM ShoppingDetail e WHERE e.idorders.idorders = :idorders and e.idorders.userIduser = :user and e.paid = FALSE")
	Double getAmountPaid(@Param("idorders") Integer idorders , @Param("user") User user);
	
	
}
