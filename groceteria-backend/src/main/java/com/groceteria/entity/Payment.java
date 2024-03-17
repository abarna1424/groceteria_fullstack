package com.groceteria.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="Payment_Table")
public class Payment {
	@Id
	@SequenceGenerator(name = "generator4", sequenceName = "gen4", initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator4")
	@Column(name = "payment_id")
	private long paymentId;
	
	
	@Column(name="Total_Price")
	private double totalPrice;
	
	
	@Column(name="Order_Id",unique = true)
	private long orderId;
	
	
	@Column(name="Paid_Date")
	private LocalDate paidDate;
	
	
	@Column(name="Paid_Amount",nullable = false)
	private double paidAmount;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="User_id")
	@JsonIgnore
	private User user;
}
