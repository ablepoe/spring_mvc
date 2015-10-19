package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20160617
 * -user auth of every function
 *
 */
public class UserFunctionAuth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1754874665303134951L;

	private String user_Id;
	private String func_Id;
	private String func_name;
	private String authority;
	
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getFunc_Id() {
		return func_Id;
	}
	public void setFunc_Id(String func_Id) {
		this.func_Id = func_Id;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
