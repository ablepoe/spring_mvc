package com.demo2.dao.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo2.dao.BCountDao;

@Repository("BCountDao2")
public class BCountDaoImpl implements BCountDao{
	
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
