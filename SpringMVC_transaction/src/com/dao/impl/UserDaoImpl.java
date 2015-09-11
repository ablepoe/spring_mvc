package com.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;


@Repository
public class UserDaoImpl implements UserDao{

	@Resource(name="crmJdbcDb")
	private NamedParameterJdbcTemplate crmJdbcDb;
	
	@Transactional(propagation=Propagation.NESTED)
	@Override
	public void addUser() {
		Map[] map = new HashMap[5];
		String sql = "insert into USER values(:id,:username,:password)";
		for (int i = 0; i < map.length; i++) {
			Map<String,Object> inMap = new HashMap<String,Object>();
			inMap.put("id", i);
			inMap.put("username", i+"u");
			inMap.put("password", i+"p");
			map[i] = inMap;
		}
		crmJdbcDb.batchUpdate(sql, map);
//		throw new RuntimeException();
	}
	
}
