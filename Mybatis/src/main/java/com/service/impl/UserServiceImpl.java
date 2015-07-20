package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.UserOperation;
import com.entity.User;
import com.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userMapper")
	private UserOperation userOperation;
	
	@Override
	public User selectUserByID(int id) {
		return userOperation.selectUserByID(id);
	}

	@Override
	public List<User> selectUsersByName(String username) {
		return userOperation.selectUsersByName(username);
	}

	@Override
	public void insertUser(User user) {
		userOperation.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userOperation.updateUser(user);	
	}

	@Override
	public void deleteUser(int id) {
		userOperation.deleteUser(id);
	}

}
