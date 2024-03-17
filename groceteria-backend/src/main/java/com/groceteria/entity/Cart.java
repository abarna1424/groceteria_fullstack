package com.groceteria.entity;

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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="Cart_Table")
public class Cart {
	
	@Id
	@SequenceGenerator(name = "generator5", sequenceName = "gen5", initialValue = 5550)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator5")
	@Column(name = "Cart_Id")
	private Long cartId;
	
	@NotNull
	@Column(name="Quantity")
	private long quantity;
	
	@NotNull
	@Column(name="Mrp_Price",nullable = false)
	private double mrpPrice;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Item_Id")
	private Item item;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="User_Id")
	private User user;
	
	public Cart() {
		
	}

}
