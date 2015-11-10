package com.demo1.dao.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo1.dao.BCountDao;

@Repository("BCountDao")
public class BCountDaoImpl implements BCountDao{
	
	@Resource(name = "JdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name = "druidDataSource")
	private DruidDataSource druidDataSource;
	
	@Override
	public void moneyIn(String username, int count) throws SQLException {
		String sql = "update transfer set count =count +"+count+" where username = '"+username+"'";
		jdbcTemplate.execute(sql);
//		druidDataSource.getConnection().createStatement().execute(sql);
	}
	
	@Override
	public void moneyOut(String username, int count) throws SQLException {
		String sql = "update transfer set count =count -"+count+" where username = '"+username+"'";
		jdbcTemplate.execute(sql);
//		druidDataSource.getConnection().createStatement().execute(sql);
	}

}
