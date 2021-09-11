package com.example.casestudy_spring_webbanquanao.repository;

import java.util.List;

import com.example.casestudy_spring_webbanquanao.models.Order;
import com.example.casestudy_spring_webbanquanao.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;



public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByUser(User user);
	
	@EntityGraph(attributePaths = { "cartItems", "payment", "shipping" })
	Order findEagerById(Long id);

}
