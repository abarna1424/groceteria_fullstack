package com.groceteria.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.entity.Cart;
import com.groceteria.entity.Order;
import com.groceteria.entity.TransactionDetails;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.CartRepository;
import com.groceteria.repository.OrderRepository;
import com.groceteria.service.CartService;
import com.groceteria.service.ItemService;
import com.groceteria.service.OrderService;
import com.groceteria.service.UserService;
import com.razorpay.RazorpayClient;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private static final String ORDER_PLACED = "Placed";

    private static final String KEY = "rzp_test_AXBzvN2fkD4ESK";
    private static final String KEY_SECRET = "bsZmiVD7p1GMo6hAWiy4SHSH";
    private static final String CURRENCY = "INR";
	
	@Autowired
	public OrderRepository orderRepository;
	
	@Autowired
	public ItemService itemService;
	
	@Autowired
	public CartService cartService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public CartRepository c;
	
	

	public OrderServiceImpl(OrderRepository orderRepository, ItemService itemService, CartService cartService,
			UserService userService, CartRepository c) {
		super();
		this.orderRepository = orderRepository;
		this.itemService = itemService;
		this.cartService = cartService;
		this.userService = userService;
		this.c = c;
	}

	@Override
	public Order addOrder(Order order, Integer userId, long cartId) {
		Cart cart = cartService.getCartById(cartId);
		User user = userService.getUserById(userId);
		order.setTotalPrice(order.getMrpPrice() * cart.getQuantity());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderDate(order.getOrderDate());
		order.setMrpPrice(order.getMrpPrice());
		order.setQuantity(cart.getQuantity());
		System.out.println(">>>>>" + cart.getQuantity());
		order.setUser(user);
		Order o = orderRepository.save(order);
		c.deleteById(cartId);
		return o;
	}

	@Override
	public Order getOrderById(long orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));

	}

	@Override
	public Order updateOrder(Order order, long orderId) {
		Order existingOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
		existingOrder.setTotalPrice(order.getMrpPrice());
		// existingOrder.setPrice(order.getPrice());
		existingOrder.setPaymentStatus(order.getPaymentStatus());
		existingOrder.setMrpPrice(order.getMrpPrice());
		existingOrder.setOrderStatus(order.getOrderStatus());
		existingOrder.setUser(order.getUser());
		// existingOrder.setCartId(order.getCartId());
		existingOrder.setOrderDate(order.getOrderDate());
		// existingOrder.setCart(order.getCart());
		orderRepository.save(existingOrder);
		return existingOrder;
	}

	@Override
	public List<Order> getOrderByUserId(Integer userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		java.util.Date d = new java.util.Date(month, day, year);
		System.out.println(d);
		List<Order> orders = orderRepository.findByUserUserId(userId);
		System.out.println(orders);
		return orderRepository.findByUserUserId(userId);
	}

	@Override
	public Order addOrderItem(Order order, Integer userId) {
		User user = userService.getUserById(userId);
		order.setTotalPrice(order.getTotalPrice());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderDate(order.getOrderDate());
		order.setUser(user);
		order.setItem(order.getItem());
		System.out.println("################"+ order.getItem().get(0).getQuantity());
		Order o = orderRepository.save(order);
		return o;
	}

	@Override
	public void deleteOrder(long orderId) {
		orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
		orderRepository.deleteById(orderId);

	}
//-----------------------------------------------RAZORPAY SECTION-----------------------------------------------------------------
	@Override
	public TransactionDetails createTransaction(Double amount) {
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount * 100));
			jsonObject.put("currency", CURRENCY);
			
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			 com.razorpay.Order order = razorpayClient.orders.create(jsonObject);

	            TransactionDetails transactionDetails = prepareTransactionDetails(order);
	            return transactionDetails;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
			
	}
	
	private TransactionDetails prepareTransactionDetails(com.razorpay.Order order) {
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
        return transactionDetails;
    }
//----------------------------------------------------------------------------------------------------------------------

	@Override
	public List<Order> getAllOrders() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		java.util.Date d = new java.util.Date(month, day, year);
		System.out.println(d);
		List<Order> orders = orderRepository.findAll();
		System.out.println(orders);
		return orderRepository.findAll();
	}

	


	

}
