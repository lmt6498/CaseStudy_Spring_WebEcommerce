package com.example.casestudy_spring_webbanquanao.service;


import com.example.casestudy_spring_webbanquanao.models.CartItem;
import com.example.casestudy_spring_webbanquanao.models.Product;
import com.example.casestudy_spring_webbanquanao.models.ShoppingCart;
import com.example.casestudy_spring_webbanquanao.models.User;

public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addProductToShoppingCart(Product product, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
