package com.example.casestudy_spring_webbanquanao.service;

import com.example.casestudy_spring_webbanquanao.models.*;

import java.util.List;



public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
