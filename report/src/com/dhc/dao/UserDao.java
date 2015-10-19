package com.dhc.dao;

import java.util.List;

import com.dhc.entity.HibernateUserFunctionAuth;
import com.dhc.entity.User;
import com.dhc.entity.UserFunctionAuth;

/**
 * 
 * @author hanliang 20160617
 * -user interface
 *
 */
public interface UserDao {
	
	/**
	 * check user
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkUser(String username, String password);
	
	/**
	 * check user owned role
	 * @param username
	 * @return
	 */
	public List<User> checkUserByName(String username);
	
	/**
	 * get user able to get role
	 * @param username
	 * @return
	 */
	public List<User> checkUserRoleListByName(String username);
	
	/**
	 * add user role
	 * @param user_Id
	 * @param user_role
	 * @return
	 */
	public boolean addUserRoleListById(int user_Id, List<String> user_role);
	
	/**
	 * delete user role
	 * @param user_Id
	 * @param user_role
	 * @return
	 */
	public boolean deleteUserRoleListById(int user_Id, List<String> user_role);
	
	/**
	 * get user single function auth
	 * @param user_id
	 * @return
	 */
	public List<UserFunctionAuth> getAllFunctionAuth(int user_id);
	
	/**
	 * set user function auth
	 * @param hibernateUsers
	 */
	public void saveOrUpdate(List<HibernateUserFunctionAuth> hibernateUsers);
	
	/**
	 * check checkRegisterUser
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkRegisterUser(String username);
	
	/**
	 * register user
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean registerUser(String username, String password);
}
