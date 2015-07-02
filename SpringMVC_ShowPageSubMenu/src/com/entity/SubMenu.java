package com.entity;

import java.io.Serializable;

public class SubMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4332201454280056692L;

	private String menuId;
	private String subMenuId;
	private String subMenuName;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
	public String getSubMenuName() {
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
}

