package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.MenuDao;
import com.entity.Menu;
import com.service.MenuService;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

	@Resource(name="MenuDao")
	private MenuDao MenuDaoImpl;
	
	@Override
	public List<Menu> getMenuList(String user_Id) {
		return MenuDaoImpl.getMenu(user_Id);
	}

	@Override
	public List<Menu> getSubMenu(String user_Id) {
		return MenuDaoImpl.getSubMenu(user_Id);
	}

}
