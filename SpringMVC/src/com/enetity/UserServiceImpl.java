package com.enetity;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
	public UserServiceImpl(){
		System.out.println("init userServiceImpl");
	}
}
