package com.entity;

import java.io.Serializable;

public class TempParam implements Serializable{

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5859527013784877584L;

}
