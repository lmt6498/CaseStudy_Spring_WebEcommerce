package com.example.casestudy_spring_webbanquanao;

import java.util.Arrays;

import com.example.casestudy_spring_webbanquanao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class StartupRunner implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		userService.createUser("admin", "admin", "admin@admin.com", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));	
		userService.createUser("tidoaries", "123456", "tidoaries@gmail.com", Arrays.asList("ROLE_USER"));
	}
}

