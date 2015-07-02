package com.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4807518646424894272L;

	private String menuId;
	private String menuName;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
