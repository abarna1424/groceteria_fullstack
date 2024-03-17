package com.groceteria.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="Order_Table")
public class Order {
	
	@Id
	@SequenceGenerator(name = "generator3", sequenceName = "gen", initialValue = 300)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator3")
	@Column(name="item_id")
	private long orderId;
	
	@NotNull
	@Column(name="Mrp_Price")
	private double mrpPrice;
	
	@NotNull
	@Column(name="Quantity")
	private long quantity;
	
	@NotNull
	@Column(name="Total_Price")
	private double totalPrice;
	
	@NotEmpty
	@Column(name="Order_Status")
	private String orderStatus;
	
	@NotEmpty
	@Column(name="Payment_Status")
	private String paymentStatus;
	
	
	@Column(name="Order_Date")
	private Date orderDate;
	
	@NotEmpty(message = "Item Name is required")
	@Column(name="Item_Name")
	private String itemName;
	
	@NotEmpty
	@Column(name="Item_Image")
	private String image;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="User_ID")
	private User user;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Item> item;
	
	
	public Order() {
		
	}

	
	
}
