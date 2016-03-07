package com.dao;

import org.springframework.data.repository.Repository;

import com.entity.AccountInfo;

@org.springframework.stereotype.Repository("AccountInfoDao")
public interface AccountInfoDao extends  Repository<AccountInfo, Long>{
	public AccountInfo findById(Long id);
}
