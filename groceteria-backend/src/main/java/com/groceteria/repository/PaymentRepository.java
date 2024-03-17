package com.groceteria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groceteria.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
   public List<Payment> findByOrderId(long orderId);
}
