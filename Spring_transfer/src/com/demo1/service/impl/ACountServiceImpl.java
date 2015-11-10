package com.demo1.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo1.dao.ACountDao;
import com.demo1.service.ACountService;

@Service("ACountService")
public class ACountServiceImpl implements ACountService{
	
	@Resource(name = "ACountDao")
	private ACountDao ACountDaoImpl; 
	
	@Override
	public void moneyIn(String username, int count) throws SQLException {
		ACountDaoImpl.moneyIn(username, count);
	}
	
	@Override
	public void moneyOut(String username, int count) throws SQLException {
		ACountDaoImpl.moneyOut(username, count);
	}

}
