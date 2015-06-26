package com.service;

import com.entity.MyUser;

public interface MyUserService {

	public MyUser findByNameAndPassword(String username, String password);
	
}
