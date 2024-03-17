package com.groceteria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@NamedQuery(name = "item.findByMrpPrice", query = "select i from Item i where i.mrpPrice = :mrpPrice")
@Table(name="Item_Table")
public class Item {
	
	@Id
	@SequenceGenerator(name = "generator2", sequenceName = "gen2", initialValue = 500)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator2")
	@Column(name="item_id")
	private long itemId;
	
	@NotEmpty(message = "Item Name is required")
	@Column(name="Item_Name",nullable = false, length=30)
	private String itemName;
	
	@NotEmpty
	@Column(name="Item_Image")
	private String image;
	
	@NotEmpty(message = "Item description is required")
	@Column(name="Description",nullable = false)
	private String description;
	
	@NotNull
	@Column(name="MRP_Price",nullable = false)
	private double mrpPrice;
	
	@NotNull
	@Column(name="Quantity",nullable=false)
	private long quantity;
	
	
	private Category category;
	
	

	
}
