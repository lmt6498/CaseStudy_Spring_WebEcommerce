package com.example.casestudy_spring_webbanquanao.service;


import com.example.casestudy_spring_webbanquanao.models.User;

import java.util.List;



public interface UserService {
	
	User findById(Long id);
	
	User findByUsername(String username);
		
	User findByEmail(String email);
		
	void save(User user);
	
	User createUser(String username, String email,  String password, List<String> roles);

}
