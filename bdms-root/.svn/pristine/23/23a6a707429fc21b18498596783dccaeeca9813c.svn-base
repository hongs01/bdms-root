package com.bdms.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdms.auth.dao.UserDao;
import com.bdms.entity.auth.User;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
    private	UserDao userDao;
	
	@Override
	public void saveUser(User user){
		userDao.save(user);
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findOne(id);
	}

	@Override
	public void delUser(Integer id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
		
	}

	@Override
	public Page<User> finaAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return userDao.findAll(pageable);
	}

}
