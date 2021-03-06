package com.demo1.dao.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo1.dao.ACountDao;

@Repository("ACountDao")
public class ACountDaoImpl implements ACountDao{

	@Resource(name = "JdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void moneyIn(String username, int count) throws SQLException {
		String sql = "update transfer set count =count +"+count+" where username = '"+username+"'";
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public void moneyOut(String username, int count) throws SQLException {
		String sql = "update transfer set count =count -"+count+" where username = '"+username+"'";
		jdbcTemplate.execute(sql);
	}
}
