package com.groceteria.service;

import java.util.List;

import com.groceteria.entity.Cart;
import com.groceteria.entity.User;

public interface CartService {
	
	Cart addCart(Cart cart,long itemId,Integer userId);
	
	List<Cart> getAllCarts();
	
	Cart getCartById(long cartId);
	
	Cart updateCart(Cart cart, long cartId);
	
	void deleteCart(long cartId);
	
	void deleteCartByUser(User u);
	
	void deleteCartByQuanity(long cartId, long avaiableQuantity);

}
