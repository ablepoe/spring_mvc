package com.dao;

import java.util.Map;

import com.entity.UserAuth;

public interface AuthorityDao {

	public Map<String, UserAuth> getUserAuth(String userId);
	
}
