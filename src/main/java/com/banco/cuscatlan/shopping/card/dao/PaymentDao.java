package com.banco.cuscatlan.shopping.card.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository; 
import com.banco.cuscatlan.shopping.card.models.Payment; 

@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer>{

	@Query("SELECT e FROM Payment e WHERE e.user = :user ")
	List<Payment> getPayments(Integer user);
	
	
}
