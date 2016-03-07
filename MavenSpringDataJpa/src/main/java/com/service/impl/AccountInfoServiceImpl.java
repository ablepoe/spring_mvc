package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.AccountInfoDao;
import com.entity.AccountInfo;
import com.service.AccountInfoService;

@Service("AccountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService{
	
	@Resource(name = "AccountInfoDao")
	private AccountInfoDao accountInfoDao;
	
	public AccountInfo getAccountInfo(Long id) {
		return accountInfoDao.findById(id);
	}

}
