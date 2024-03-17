package com.groceteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.entity.Order;
import com.groceteria.entity.TransactionDetails;
import com.groceteria.service.OrderService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//adding order details
	@PostMapping("/{userId}/{cartId}")
	public ResponseEntity<Order> addOrder(@PathVariable Integer userId, @PathVariable long cartId,@RequestBody Order order) {
	return new ResponseEntity<Order>(orderService.addOrder(order, userId, cartId), HttpStatus.CREATED);
	}
	
	@PutMapping("{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable("orderId") long orderId, @RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.updateOrder(order, orderId), HttpStatus.OK);
	}
	
	@GetMapping()
	public List<Order> getAllOrders() {

		return orderService.getAllOrders();
	}
	
	@GetMapping("{userId}")
	public List<Order> getOrderByUserId(@PathVariable Integer userId) {
		return orderService.getOrderByUserId(userId);
	}
	
	@DeleteMapping("{orderId}")
	public ResponseEntity<Boolean> deleteBooking(@PathVariable("orderId") long orderId) {
		orderService.deleteOrder(orderId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	
	@PostMapping("/addOrder/{userId}")
	public ResponseEntity<Order> addOrderItems(@PathVariable Integer userId, @RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.addOrderItem(order, userId), HttpStatus.CREATED);
	}
	
	@GetMapping({"/createTransaction/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name = "amount") Double amount) {
        return orderService.createTransaction(amount); //orderDetailService.createTransaction(amount);
    }


}
