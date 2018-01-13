package com.tripthyst.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripthyst.webapp.mapper.UserMapper;
import com.tripthyst.webapp.model.UserModel;

@Service
public class UserServiceDatabase implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserModel getUser(String username) {
		
		return userMapper.selectUser(username);
	}

}
