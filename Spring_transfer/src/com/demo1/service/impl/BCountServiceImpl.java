package com.demo1.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo1.dao.BCountDao;
import com.demo1.service.BCountService;

@Service("BCountService")
public class BCountServiceImpl implements BCountService{

	@Resource(name = "BCountDao")
	private BCountDao BCountDaoImpl; 
	
	@Override
	public void moneyIn(String username, int count) throws SQLException {
		BCountDaoImpl.moneyIn(username, count);
	}
	
	@Override
	public void moneyOut(String username, int count) throws SQLException {
		BCountDaoImpl.moneyOut(username, count);
	}	
	
}
