package com.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.entity.UserAuth;

public interface AuthorityService {
	
	public Map<String, UserAuth> getUserAuth(String userId);
	
}
