package com.banco.cuscatlan.shopping.card.dao;

import java.util.List; 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository; 
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; 
import com.banco.cuscatlan.shopping.card.models.Product;
import com.banco.cuscatlan.shopping.card.models.User; 
 
@Repository
public interface ProductDao  extends CrudRepository<Product, Integer>{
	 
	@Modifying
	@Transactional
	@Query("DELETE FROM Product p WHERE p.iduser = :id and p.idproductscar = :idProduct")
	Integer deleteById(@Param("id") User id , @Param("idProduct") Integer idProduct);
	
	@Query("SELECT COUNT(e.idproductscar) FROM Product e WHERE e.iduser = :id")
	Integer getCountOfProductsByUser(@Param("id") User id);
	  
	
	@Query("SELECT e FROM Product e WHERE e.iduser = :id")
	List<Product> getProductsByUser(@Param("id") User id);
	
}
