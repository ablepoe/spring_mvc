package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20160617
 * -user entity
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2694952780129606210L;

	private String user_Id;
	private String userName;
	private String password;
	
	private String user_role_id;
	private String user_role_name;
	
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(String user_role_id) {
		this.user_role_id = user_role_id;
	}
	public String getUser_role_name() {
		return user_role_name;
	}
	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}
}
