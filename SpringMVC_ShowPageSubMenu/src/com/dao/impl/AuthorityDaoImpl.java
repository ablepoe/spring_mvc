package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.AuthorityDao;
import com.entity.UserAuth;

@Repository("AuthorityDao")
public class AuthorityDaoImpl implements AuthorityDao{
	
    @Resource(name = "npJdbcHL_Db")
    protected NamedParameterJdbcTemplate npJdbcHL_Db;

	@Override
	public Map<String, UserAuth> getUserAuth(String userId) {
		StringBuffer sb = new StringBuffer();
		//get the userAuth
		sb.append(" SELECT 0 AUTH_TYPE, UR.USER_ID , RF.FUNC_ID, RF.FUNC_AUTH FROM HL_ROLE_FUNC RF");
		sb.append(" INNER JOIN HL_USER_ROLE UR ON UR.ROLE_ID = RF.ROLE_ID");
		sb.append(" WHERE UR.USER_ID = :user_id");
		//get the special userAuth
		sb.append(" UNION ALL"); 
		sb.append(" SELECT 1 AUTH_TYPE, UF.USER_ID, UF.FUNC_ID, UF.AUTHORITY");
		sb.append(" FROM HL_USER_FUNC UF INNER JOIN HL_FUNC FUNC ON UF.FUNC_ID = FUNC.FUNC_ID");
		sb.append(" WHERE UF.USER_ID = :user_id");
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("user_id", userId);
		final Map<String, UserAuth> userAuthList = new HashMap<String, UserAuth>(); 
		npJdbcHL_Db.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String func_id = rs.getString("FUNC_ID");
				//the func_id already exist in map
				if(userAuthList.containsKey(func_id)){
					//different charactor (HL_ROLE_FUNC)
					if("0".equals(rs.getString("AUTH_TYPE"))){
						if("W".equals(rs.getString("FUNC_AUTH"))){
							userAuthList.get(func_id).setFuncAuth(rs.getString("FUNC_AUTH"));
						}
					//special charactor (HL_USER_FUNC)
					}else if("1".equals(rs.getString("AUTH_TYPE"))){
						userAuthList.get(func_id).setFuncAuth(rs.getString("FUNC_AUTH"));
					}
				}else{
					UserAuth userAuth = new UserAuth();
					userAuth.setFuncId(func_id);
					userAuth.setFuncAuth(rs.getString("FUNC_AUTH"));
					userAuthList.put(func_id, userAuth);
				}
				
				
			}
		});
		return userAuthList;
	}
}
