package com.bdms.auth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.entity.auth.User;


	@Service
	public interface UserService {
		
		void saveUser(User user);
		
		User getUserById(Integer id);
		
		void delUser( Integer id);
		
		Page<User> finaAllByPage(Pageable pageable);
	}
 


