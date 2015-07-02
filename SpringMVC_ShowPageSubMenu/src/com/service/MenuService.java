package com.service;

import java.util.List;

import com.entity.Menu;

public interface MenuService {
	
	public List<Menu> getMenuList(String user_Id);
	
	public List<Menu> getSubMenu(String user_Id);
}
