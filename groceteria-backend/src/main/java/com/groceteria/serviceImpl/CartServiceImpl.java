package com.groceteria.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.entity.Cart;
import com.groceteria.entity.Item;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.CartRepository;
import com.groceteria.service.CartService;
import com.groceteria.service.ItemService;
import com.groceteria.service.UserService;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ItemService itemService;
    

	public CartServiceImpl(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}

	@Override
	public Cart addCart(Cart cart, long itemId, Integer userId) {
		Item item=itemService.getItemByItemId(itemId);
		User user=userService.getUserById(userId);
		List<Cart>crl=this.getAllCarts();
		int flag=0;
		Cart existingCart=null;
		if(crl.size()>0) {
			for(int i=0; i<crl.size();i++) {
				Cart c=this.getCartById(crl.get(i).getCartId());
				if(c.getUser().getUserId() ==userId && c.getItem().getItemId()==itemId) {
					flag=1;
					existingCart=c;
				}
			}
		}
		item.setQuantity(item.getQuantity()-cart.getQuantity());
		if(flag==1 && existingCart !=null) {
			existingCart.setQuantity(existingCart.getQuantity()+cart.getQuantity());
			existingCart.setMrpPrice(item.getMrpPrice());
			existingCart.setUser(user);
			System.out.println("111111111111111111111111111111111");
			return this.updateCart(existingCart, existingCart.getCartId());
		}
		else {
			cart.setItem(item);
			cart.setMrpPrice(item.getMrpPrice());
			cart.setUser(user);
			System.out.println("2222222222222222222222222222222222222222");
			return cartRepository.save(cart);
		}
	}

	@Override
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getCartById(long cartId) {
		return cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart", "Id", cartId));
	}

	@Override
	public Cart updateCart(Cart cart, long cartId) {
		Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart", "Id", cartId));
		existingCart.setQuantity(cart.getQuantity());
		existingCart.setMrpPrice(cart.getMrpPrice());
		existingCart.setCartId(cart.getCartId());
		existingCart.setItem(cart.getItem());
		existingCart.setUser(cart.getUser());
		cartRepository.save(existingCart);
		
		return existingCart;
	}
	
	@Override
	public void deleteCart(long cartId) {
		Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart", "Id", cartId));
		Item item = itemService.getItemByItemId(existingCart.getItem().getItemId());
		System.out.println("^%%%^^^ " + item.getItemId());
		System.out.println("^%%%^^^Quinity^^" + item.getQuantity());
		item.setQuantity(item.getQuantity());
		itemService.updateItem(item, item.getItemId());
		cartRepository.deleteById(cartId);
	}

	@Override
	public void deleteCartByQuanity(long cartId, long avaiableQuantity) {
		Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart", "Id", cartId));
		Item item = itemService.getItemByItemId(existingCart.getItem().getItemId());
		System.out.println("^%%%^^^ " + item.getItemId());
		System.out.println("^%%%^^^Quinity^^" + item.getQuantity());
		item.setQuantity(item.getQuantity());
		itemService.updateItemWithQuantity(item, item.getItemId(), avaiableQuantity);
		cartRepository.deleteById(cartId);
	}

	@Override
	public void deleteCartByUser(User u) {
		cartRepository.deleteCartByUser(u);
		
	}
    
}