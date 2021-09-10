package com.example.casestudy_spring_webbanquanao.repository;

import com.example.casestudy_spring_webbanquanao.models.security.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
