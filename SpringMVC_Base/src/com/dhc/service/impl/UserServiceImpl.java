package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.UserDao;
import com.dhc.entity.HibernateUserFunctionAuth;
import com.dhc.entity.User;
import com.dhc.entity.UserFunctionAuth;
import com.dhc.service.UserService;


/**
 * 
 * @author hanliang 20160617
 * -userServiceImpl with userDao interface
 *
 */
@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Resource(name = "UserDao")
	private UserDao userDao;

	@Override
	public User checkUser(String username, String password) {
		return userDao.checkUser(username, password);
	}

	@Override
	public List<User> checkUserByName(String username) {
		return userDao.checkUserByName(username);
	}

	@Override
	public List<User> checkUserRoleListByName(String username) {
		return userDao.checkUserRoleListByName(username);
	}

	@Override
	public boolean addUserRoleListById(String user_Id, List<String> user_role) {
		return userDao.addUserRoleListById(user_Id, user_role);
	}

	@Override
	public boolean deleteUserRoleListById(String user_Id, List<String> user_role) {
		return userDao.deleteUserRoleListById(user_Id, user_role);
	}

	@Override
	public List<UserFunctionAuth> getAllFunctionAuth(String user_id) {
		return userDao.getAllFunctionAuth(user_id);
	}

	@Override
	public void saveOrUpdate(List<HibernateUserFunctionAuth> hibernateUsers) {
		userDao.saveOrUpdate(hibernateUsers);
	}
}
