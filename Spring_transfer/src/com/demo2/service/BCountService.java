package com.demo2.service;

import java.sql.SQLException;

public interface BCountService {
	
	public void moneyIn(String username, int count) throws SQLException;
	
	public void moneyOut(String username, int count) throws SQLException;
}
