package com.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional(value="transactionManager")
@Repository
public class UserDaoImpl {

	@Resource(name="remotePosDataSource")
	private DataSource remotePosDataSource;
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource(name="crmJdbcDb")
	private NamedParameterJdbcTemplate crmJdbcDb;
	
	
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
	
	
	public void getCount() throws SQLException{
		User user = (User)sessionFactory.getCurrentSession().get(User.class, 1);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(System.out,user );
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public int getConnectionType2() throws SQLException{
		String sql = "SELECT COUNT(1) FROM CONNECTIONTEST";
		int i = crmJdbcDb.queryForObject(sql, new HashMap<String,String>(), Integer.class);
		return i;
	}
	
}
