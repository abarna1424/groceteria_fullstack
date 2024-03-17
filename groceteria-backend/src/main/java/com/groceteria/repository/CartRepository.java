package com.groceteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groceteria.entity.Cart;
import com.groceteria.entity.Item;
import com.groceteria.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
   
	void deleteCartByUser(User u);

	
}
