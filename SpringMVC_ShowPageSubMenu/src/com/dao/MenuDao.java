package com.dao;

import java.util.List;

import com.entity.Menu;

public interface MenuDao {
	
	public List<Menu> getMenu(String user_Id);
	
	public List<Menu> getSubMenu(String user_Id);
}
