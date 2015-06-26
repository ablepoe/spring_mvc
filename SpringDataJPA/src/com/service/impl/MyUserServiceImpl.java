package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.MyUser;
import com.repository.MyUserRepository;
import com.service.MyUserService;

@Service("MyUserService")
public class MyUserServiceImpl implements MyUserService{

	@Autowired
	private MyUserRepository myUserRepository;

	@Override
	public MyUser findByNameAndPassword(String username, String password) {
		MyUser myUser = myUserRepository.findByNameAndPassword(username, password);
		return myUser;
	}
}
