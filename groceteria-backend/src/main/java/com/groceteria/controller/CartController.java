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

import com.groceteria.entity.Cart;
import com.groceteria.service.CartService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("{userId}/{itemId}")
	public ResponseEntity<Cart>addCart(@Valid@RequestBody Cart cart, @PathVariable long itemId, @PathVariable Integer userId){
		System.out.println("********");
		return new ResponseEntity<Cart>(cartService.addCart(cart, itemId, userId), HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public List<Cart> getAllCarts(){
		return cartService.getAllCarts();
	}
	
	@GetMapping("Cart/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("id")long cartId){
		return new ResponseEntity<Cart>(cartService.getCartById(cartId),HttpStatus.OK);
	}
	
	@PutMapping("/{cartId}")
	public ResponseEntity<Cart>updateCart(@Valid@PathVariable("cartId")long cartId, @RequestBody Cart cart ){
		return new ResponseEntity<Cart>(cartService.updateCart(cart, cartId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{cartId}")
	public ResponseEntity<Boolean>deleteCart(@PathVariable long cartId){
		cartService.deleteCart(cartId);
		boolean flag=true;
		return new ResponseEntity<Boolean>(flag,HttpStatus.OK);
	}
	
	@DeleteMapping("/{cartId}/{avaiableQuantity}")
	public ResponseEntity<Boolean>deleteCartByQuantity(@PathVariable long cartId, @PathVariable long avaiableQuantity){
		cartService.deleteCartByQuanity(cartId, avaiableQuantity);
		boolean flag=true;
		return new ResponseEntity<Boolean>(flag,HttpStatus.OK);
	}
	
}
