package com.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.AuthorityDao;
import com.entity.UserAuth;
import com.service.AuthorityService;

@Service("AuthorityService")
public class AuthorityServiceImpl implements AuthorityService {

	@Resource(name="AuthorityDao")
	private AuthorityDao authorityDaoImpl;
	
	@Override
	public Map<String, UserAuth> getUserAuth(String userId) {
		return authorityDaoImpl.getUserAuth(userId);
	}

}
