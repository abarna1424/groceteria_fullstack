package com.groceteria.service;

import java.util.List;

import com.groceteria.entity.Payment;

public interface PaymentService {
	
	Payment addPayment(Payment payment,long orderId,Integer userId);
	
	List<Payment> getAllPayments();
	
	Payment getPaymentById(long paymentId);
	
	void deletePayment(long paymentId);
	
	public List<Payment> getAllPaymentsByUserId(Integer userId);
}
