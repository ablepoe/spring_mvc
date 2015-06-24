package com.enetity;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	
	public void init(){
		System.out.println("init-method User");
	}
	
	@Autowired
	private UserService userService;
	
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
