package com.service;

import java.util.List;

import com.entity.User;

public interface UserService {

	public User selectUserByID(int id);
	public List<User> selectUsersByName(String username);
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	
}
