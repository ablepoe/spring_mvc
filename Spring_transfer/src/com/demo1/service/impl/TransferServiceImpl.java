package com.demo1.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo1.service.ACountService;
import com.demo1.service.BCountService;
import com.demo1.service.TransferService;


@Service("TransferService")
public class TransferServiceImpl implements TransferService{

	@Resource(name ="ACountService")
	private ACountService ACountServiceImpl;
	
	@Resource(name ="BCountService")
	private BCountService BCountServiceImpl;
	
	@Override
	public void transfer() throws SQLException {
		ACountServiceImpl.moneyOut("aaa", 200);
		int i = 1/0;
		BCountServiceImpl.moneyIn("bbb", 200);
	}

	
	
}
