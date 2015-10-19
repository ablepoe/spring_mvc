package com.dhc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhc.dao.UserDao;
import com.dhc.entity.HibernateUserFunctionAuth;
import com.dhc.entity.User;
import com.dhc.entity.UserFunctionAuth;
import com.dhc.repository.UserRepository;
/**
 * 
 * @author hanliang 20160617
 * -user dao implements
 *
 */
@Transactional(value="transactionManager")
@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
    @Resource(name = "userRepository")
	private UserRepository userRepository;
    
	@Override
	public User checkUser(String username, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT HU.USER_ID USER_ID, HU.USERNAME USERNAME, HU.PASSWORD PASSWORD FROM REPORTFORM_USER HU WHERE ");
		sb.append("HU.USERNAME=:username AND HU.PASSWORD=:password");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		final List<User> result = new ArrayList<User>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
//		npJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				int user_Id = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				user.setUser_Id(user_Id);
				user.setUserName(username);
				user.setPassword(password);
				result.add(user);
			}
		});
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> checkUserByName(String username) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT USR.USER_ID USER_ID, ROL.ROLE_ID ROLE_ID, ROL.ROLE_NAME ROLE_NAME FROM REPORTFORM_USER USR ");
		sb.append("LEFT JOIN REPORTFORM_USER_ROLE USROLE ON USR.USER_ID = USROLE.USER_ID ");
		sb.append("LEFT JOIN REPORTFORM_ROLE ROL ON USROLE.ROLE_ID = ROL.ROLE_ID ");
		sb.append("WHERE USR.USERNAME = :username ");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", username);
		final List<User> result = new ArrayList<User>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
//		npJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				int user_id = rs.getInt("USER_ID");
				String user_role_id = rs.getString("ROLE_ID");
				String user_role_name = rs.getString("ROLE_NAME");
				user.setUser_Id(user_id);
				user.setUser_role_id(user_role_id);
				user.setUser_role_name(user_role_name);
				result.add(user);
			}
		});
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Override
	public List<User> checkUserRoleListByName(String username) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT ROL.ROLE_NAME ROLE_NAME,ROL.ROLE_ID ROLE_ID FROM REPORTFORM_ROLE ROL ");
		sb.append("LEFT JOIN REPORTFORM_USER_ROLE USROLE ON USROLE.ROLE_ID = ROL.ROLE_ID ");
		sb.append("LEFT JOIN REPORTFORM_USER USR ON USR.USER_ID = USROLE.USER_ID ");
		sb.append("WHERE ROL.ROLE_ID NOT IN ");
		sb.append("( ");
		sb.append("SELECT UR.ROLE_ID FROM REPORTFORM_USER_ROLE UR ");
		sb.append("LEFT JOIN REPORTFORM_USER US ON UR.USER_ID = US.USER_ID ");
		sb.append("WHERE US.USERNAME = :username ");
		sb.append(") ");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", username);
		final List<User> result = new ArrayList<User>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
//		npJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				String user_role_id = rs.getString("ROLE_ID");
				String user_role_name = rs.getString("ROLE_NAME");
				user.setUser_role_id(user_role_id);
				user.setUser_role_name(user_role_name);
				result.add(user);
			}
		});
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}

	@Override
	public boolean addUserRoleListById(int user_Id, List<String> user_role) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO REPORTFORM_USER_ROLE(USER_ID,ROLE_ID) VALUES(:user_Id,:user_role_id)");
		List<User> paramList = new ArrayList<User>();
		for (int i = 0; i < user_role.size(); i++) {
			User user = new User();
			user.setUser_Id(user_Id);
			user.setUser_role_id(user_role.get(i));
			paramList.add(user);
		}
		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(paramList.toArray());
		try {
			crmJdbcDb.batchUpdate(sb.toString(), params);
//			npJdbcDb.batchUpdate(sb.toString(), params);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteUserRoleListById(int user_Id, List<String> user_role) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM REPORTFORM_USER_ROLE WHERE USER_ID=:user_Id AND ROLE_ID=:user_role_id ");
		List<User> paramList = new ArrayList<User>();
		for (int i = 0; i < user_role.size(); i++) {
			User user = new User();
			user.setUser_Id(user_Id);
			user.setUser_role_id(user_role.get(i));
			paramList.add(user);
		}
		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(paramList.toArray());
		try {
			crmJdbcDb.batchUpdate(sb.toString(), params);
//			npJdbcDb.batchUpdate(sb.toString(), params);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<UserFunctionAuth> getAllFunctionAuth(int user_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT HF.FUNC_ID FUNC_ID, HF.FUNC_NAME FUNCNAME, HUF.USER_ID USER_ID, HUF.AUTHORITY AUTHORITY FROM REPORTFORM_FUNC HF ");
		sb.append("LEFT JOIN REPORTFORM_USER_FUNC HUF ON HF.FUNC_ID = HUF.FUNC_ID ");
		sb.append("WHERE HUF.USER_ID = :user_id ");
		sb.append("UNION ALL ");
		sb.append("SELECT HSF.FUNC_ID FUNC_ID, HSF.FUNC_NAME FUNCNAME, HSUF.USER_ID USER_ID, HSUF.AUTHORITY AUTHORITY FROM REPORTFORM_FUNC HSF ");
		sb.append("LEFT JOIN REPORTFORM_USER_FUNC HSUF ON HSF.FUNC_ID = HSUF.FUNC_ID ");
		sb.append("WHERE HSF.FUNC_ID NOT IN ");
		sb.append("( ");
		sb.append("SELECT UF.FUNC_ID FROM REPORTFORM_USER_FUNC UF ");
		sb.append("WHERE UF.USER_ID = :user_id ");
		sb.append(")");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_id", user_id);
		final List<UserFunctionAuth> result = new ArrayList<UserFunctionAuth>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
//		npJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				UserFunctionAuth userFunctionAuth = new UserFunctionAuth();
				String user_Id = rs.getString("USER_ID");
				String func_Id = rs.getString("FUNC_ID");
				String func_name = rs.getString("FUNCNAME");
				String authority = rs.getString("AUTHORITY");
				userFunctionAuth.setUser_Id(user_Id);
				userFunctionAuth.setFunc_Id(func_Id);
				userFunctionAuth.setFunc_name(func_name);
				if(authority == null){
					userFunctionAuth.setAuthority("");
				}else{
					userFunctionAuth.setAuthority(authority);	
				}
				result.add(userFunctionAuth);
			}
		});
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}
	}
	
	@Override
	public void saveOrUpdate(List<HibernateUserFunctionAuth> hibernateUsers){
		for (int i = 0; i < hibernateUsers.size(); i++) {
			HibernateUserFunctionAuth hibernateUser = hibernateUsers.get(i);
			if(hibernateUser.getAuthority().equals("")){
				userRepository.delete(hibernateUser);
			}else{
				userRepository.save(hibernateUser);
			}
		}
	}

	@Override
	public User checkRegisterUser(String username) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT HU.USER_ID USER_ID, HU.USERNAME USERNAME, HU.PASSWORD PASSWORD FROM REPORTFORM_USER HU WHERE ");
		sb.append("HU.USERNAME=:username");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", username);
		final List<User> result = new ArrayList<User>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
//		npJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				int user_Id = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				user.setUser_Id(user_Id);
				user.setUserName(username);
				user.setPassword(password);
				result.add(user);
			}
		});
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean registerUser(String username, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO REPORTFORM_USER(USERNAME,PASSWORD) VALUES(:username,:password) ");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		int count = crmJdbcDb.update(sb.toString(), paramMap);
//		int count = npJdbcDb.update(sb.toString(), paramMap);
//		System.out.println("count is "+count);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
