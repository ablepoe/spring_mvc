package com.demo2.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo2.service.ACountService;
import com.demo2.service.BCountService;
import com.demo2.service.TransferService;



@Service("TransferService2")
public class TransferServiceImpl implements TransferService{

	@Resource(name ="ACountService2")
	private ACountService ACountServiceImpl;
	
	@Resource(name ="BCountService2")
	private BCountService BCountServiceImpl;
	
	@Override
	public void transfer() throws SQLException {
		ACountServiceImpl.moneyOut("aaa", 200);
		int i = 1/0;
		BCountServiceImpl.moneyIn("bbb", 200);
	}

	
	
}
