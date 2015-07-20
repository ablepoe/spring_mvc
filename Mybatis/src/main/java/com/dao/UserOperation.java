package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.User;

public interface UserOperation {
	
	public User selectUserByID(int id);
	public List<User> selectUsersByName(String username);
	public List<User> selectUsersByName(User u);
	public List<User> chooseSelectUsersByName(User u);
	public List<User> trimSelectUserByName(User u);
	public List<User> foreachListSelectUserById(List<String> ids);
	public List<User> foreachArraySelectUserById(String[] ids);
	public List<User> foreachMapSelectUserById(Map<String,Object> ids);
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	
}
