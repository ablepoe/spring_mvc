package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.entity.DbUser;

@Repository("UserDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	@Qualifier("demoJdbcDb")
	private NamedParameterJdbcTemplate demoJdbcDb;

	@Override
	public DbUser getDatabase(String username) {
		List<DbUser> userList = getUser(username);
		DbUser user = userList.get(0);
		if(user.getUsername() == null){
			throw new RuntimeException("User does not exist!");  
		}else{
			return user;
		}
	}

    private List<DbUser> getUser(String username){
    	String sql = "select username,password,enabled from users where username=:username ";
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("username", username);
    	final List<DbUser> result = new ArrayList<DbUser>();
    	demoJdbcDb.query(sql,map,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				DbUser user = new DbUser();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEnabled(rs.getInt("enabled"));
				System.out.println(rs.getInt("enabled"));
				result.add(user);
			}
		});
    	return result;
    }

    @Override
	public List<String> getUserAuth(String username) {
		String sql = "select role from user_roles where username=:username";
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("username", username);
    	final List<String> result = new ArrayList<String>();
    	demoJdbcDb.query(sql,map,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				String role = rs.getString("role");
				result.add(role);
			}
		});
    	return result;
	}
}
