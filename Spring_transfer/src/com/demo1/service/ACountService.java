package com.demo1.service;

import java.sql.SQLException;

public interface ACountService {

	public void moneyIn(String username, int count) throws SQLException;
	
	public void moneyOut(String username, int count) throws SQLException;
	
}
