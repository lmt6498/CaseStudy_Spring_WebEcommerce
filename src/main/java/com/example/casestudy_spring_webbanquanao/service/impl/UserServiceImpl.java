package com.example.casestudy_spring_webbanquanao.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.casestudy_spring_webbanquanao.config.SecurityUtility;
import com.example.casestudy_spring_webbanquanao.models.User;
import com.example.casestudy_spring_webbanquanao.models.security.Role;
import com.example.casestudy_spring_webbanquanao.models.security.UserRole;
import com.example.casestudy_spring_webbanquanao.repository.RoleRepository;
import com.example.casestudy_spring_webbanquanao.repository.UserRepository;
import com.example.casestudy_spring_webbanquanao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findById(Long id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.get();
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User createUser(String username, String password, String email, List<String> roles) {
		User user = findByUsername(username);
		if (user != null) {
			return user;
		} else {
			user = new User();
			user.setUsername(username);
			user.setPassword(SecurityUtility.passwordEncoder().encode(password));
			user.setEmail(email);			
			Set<UserRole> userRoles = new HashSet<>();
			for (String rolename : roles) {
				Role role = roleRepository.findByName(rolename);
				if (role == null) {
					role = new Role();
					role.setName(rolename);
					roleRepository.save(role);
				}
				userRoles.add(new UserRole(user, role));
			}			
			user.setUserRoles(userRoles);
			return userRepository.save(user);
		}
	}
	
}
