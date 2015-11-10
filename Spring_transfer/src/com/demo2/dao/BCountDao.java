package com.demo2.dao;

import java.sql.SQLException;

public interface BCountDao {

	public void moneyIn(String username, int count) throws SQLException;
	
	public void moneyOut(String username, int count) throws SQLException;	
	
}
