package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20160617
 * -primaryKey of HibernateUserFunctionAuth 
 * -_PK
 *
 */
public class HibernateUserPrimaryKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8476448979664024612L;
	
	private int user_Id;
	private String func_Id;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getFunc_Id() {
		return func_Id;
	}
	public void setFunc_Id(String func_Id) {
		this.func_Id = func_Id;
	}
}
