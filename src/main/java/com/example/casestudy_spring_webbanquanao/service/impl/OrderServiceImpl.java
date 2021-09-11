package com.example.casestudy_spring_webbanquanao.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.example.casestudy_spring_webbanquanao.models.*;
import com.example.casestudy_spring_webbanquanao.repository.CartItemRepository;
import com.example.casestudy_spring_webbanquanao.repository.OrderRepository;
import com.example.casestudy_spring_webbanquanao.repository.ProductRepository;
import com.example.casestudy_spring_webbanquanao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	ProductRepository productRepository;
			
	@Override
	@Transactional
	@CacheEvict(value = "itemcount", allEntries = true)
	public synchronized Order createOrder(ShoppingCart shoppingCart, Shipping shipping, Payment payment, User user) {
		Order order = new Order();
		order.setUser(user);
		order.setPayment(payment);
		order.setShipping(shipping);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shipping.setOrder(order);
		payment.setOrder(order);			
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);				
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("In Progress");
		
		order = orderRepository.save(order);
		
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Product product = item.getProduct();
			product.decreaseStock(item.getQty());
			productRepository.save(product);
			item.setOrder(order);
			cartItemRepository.save(item);
		}		
		return order;	
	}
	
	@Override
	public Order findOrderWithDetails(Long id) {
		return orderRepository.findEagerById(id);
	}	

	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

}
