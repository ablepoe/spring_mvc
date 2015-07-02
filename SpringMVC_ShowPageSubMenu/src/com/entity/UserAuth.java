package com.entity;

import java.io.Serializable;

public class UserAuth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613338883771734694L;
	private String funcId;//权限ID，用户的iD
	private String funcName;//权限名称
	private String funcAuth;//
	
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncAuth() {
		return funcAuth;
	}
	public void setFuncAuth(String funcAuth) {
		this.funcAuth = funcAuth;
	}
}
