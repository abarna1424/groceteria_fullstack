package com.groceteria.service;

import java.util.List;

import com.groceteria.entity.Order;
import com.groceteria.entity.TransactionDetails;

public interface OrderService {
	Order addOrder(Order order, Integer userId, long cartId);

	Order getOrderById(long orderId);

	Order updateOrder(Order order, long orderId);

	List<Order> getOrderByUserId(Integer userId);

	//List<Order> getAllOrders();

	// List<Order> getAllOrdersByCartId(long cartId);
	
	Order addOrderItem(Order order,Integer userId);
	
	void deleteOrder(long orderId);
	
	TransactionDetails createTransaction(Double amount);

	List<Order> getAllOrders();
}
