package com.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4807518646424894272L;

	private String menuId;
	private String menuName;
	
	private List<Menu> subMenu;

	public List<Menu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}
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
	
	
	public boolean containFunc(String func_Id){
		for (int i = 0; i < this.subMenu.size(); i++) {
			if(this.subMenu.get(i).getMenuId().equals(func_Id) ){
				return true;
			}
		}
		return false;
	}
}
