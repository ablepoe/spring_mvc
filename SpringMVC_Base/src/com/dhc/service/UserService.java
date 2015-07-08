package com.dhc.service;

import java.util.List;

import com.dhc.entity.HibernateUserFunctionAuth;
import com.dhc.entity.User;
import com.dhc.entity.UserFunctionAuth;

/**
 * 
 * @author hanliang 20160617
 * -userService interface
 *
 */
public interface UserService {

	/**
	 * 验证用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkUser(String username, String password);
	
	/**
	 * 获取已分配的权限
	 * @param username
	 * @return
	 */
	public List<User> checkUserByName(String username);
	
	/**
	 * 获取可分配的权限
	 * @param username
	 * @return
	 */
	public List<User> checkUserRoleListByName(String username);
	
	/**
	 * 添加用户权限角色
	 * @param user_Id
	 * @param user_role
	 * @return
	 */
	public boolean addUserRoleListById(String user_Id, List<String> user_role);
	
	/**
	 * 深处用户权限角色
	 * @param user_Id
	 * @param user_role
	 * @return
	 */
	public boolean deleteUserRoleListById(String user_Id, List<String> user_role);
	
	/**
	 * 获取用户全部功能
	 * @param user_id
	 * @return
	 */
	public List<UserFunctionAuth> getAllFunctionAuth(String user_id);
	
	/**
	 * 用户单功能权限修改
	 * @param hibernateUsers
	 */
	public void saveOrUpdate(List<HibernateUserFunctionAuth> hibernateUsers);
}
