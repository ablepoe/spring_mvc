package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {

	@Resource(name="remotePosDataSource")
	private DataSource remotePosDataSource;
	
	
	public int getConnection() throws SQLException{
		Connection conn = remotePosDataSource.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(1) FROM CONNECTIONTEST";
		ResultSet rs = stmt.executeQuery(sql);
		int count = 0;
		while(rs.next()){
			count += rs.getInt(1);
		}
		return count;
	}
}
