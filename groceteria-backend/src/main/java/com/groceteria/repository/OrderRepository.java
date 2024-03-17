package com.groceteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    

	public List<Order> findByUserUserId(Integer userId);
    public void deleteByOrderId(long orderId);
	
    
	
}
