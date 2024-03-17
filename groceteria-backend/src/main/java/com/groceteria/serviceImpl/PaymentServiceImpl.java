package com.groceteria.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.entity.Order;
import com.groceteria.entity.Payment;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.OrderRepository;
import com.groceteria.repository.PaymentRepository;
import com.groceteria.service.OrderService;
import com.groceteria.service.PaymentService;
import com.groceteria.service.UserService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	 @Autowired
	 private PaymentRepository paymentRepository;
		
	@Autowired
	private OrderRepository orderRepository;

		
	@Autowired
	private UserService userService;
		
	@Autowired
	private OrderService orderService;
	
	


	public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository,
			UserService userService, OrderService orderService) {
		super();
		this.paymentRepository = paymentRepository;
		this.orderRepository = orderRepository;
		this.userService = userService;
		this.orderService = orderService;
	}

	@Override
	public Payment addPayment(Payment payment, long orderId, Integer userId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));
		System.out.println("****************"+order.getOrderId());
    	payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
//		payment.setPaidDate(order.getOrderDate());
		payment.setPaidDate(LocalDate.now());
		payment.setPaidAmount(order.getTotalPrice());
		if (payment.getTotalPrice() == payment.getPaidAmount()) {
			order.setPaymentStatus("PAID");
			order.setOrderStatus("Delivered");
		} else {

			order.setPaymentStatus("NOT-PAID");
			order.setOrderStatus("payment pending");
		}
			  User user=userService.getUserById(userId);
		    	
		    	payment.setUser(user);
		    	
		    	
		    	     //return paymentRepository.save(payment);
		    	
		
		return paymentRepository.save(payment);
		
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(long paymentId) {
		return paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payement","Id",paymentId));
	}

	@Override
	public void deletePayment(long paymentId) {
		paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payement","Id",paymentId));
		paymentRepository.deleteById(paymentId);
		
		
	}

	@Override
	public List<Payment> getAllPaymentsByUserId(Integer userId) {
		return paymentRepository.findByOrderId(userId);
	}

}
