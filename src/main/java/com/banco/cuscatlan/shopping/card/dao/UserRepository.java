package com.banco.cuscatlan.shopping.card.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository; 
import com.banco.cuscatlan.shopping.card.models.User; 

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

}
