package com.example.casestudy_spring_webbanquanao.repository;

import java.util.List;

import com.example.casestudy_spring_webbanquanao.models.CartItem;
import com.example.casestudy_spring_webbanquanao.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;



public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	@EntityGraph(attributePaths = { "product" })
	List<CartItem> findAllByUserAndOrderIsNull(User user);
	
	void deleteAllByUserAndOrderIsNull(User user);

	int countDistinctByUserAndOrderIsNull(User user);
}
