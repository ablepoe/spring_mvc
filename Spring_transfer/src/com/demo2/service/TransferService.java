package com.demo2.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface TransferService {
	@Transactional(propagation=Propagation.REQUIRED)
	public void transfer() throws SQLException;
	
}
