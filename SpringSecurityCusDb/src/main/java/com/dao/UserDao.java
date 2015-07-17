package com.dao;


import java.util.List;

import com.entity.DbUser;

public interface UserDao {

	public DbUser getDatabase(String username);
	
	public List<String> getUserAuth(String username);;
	
}
