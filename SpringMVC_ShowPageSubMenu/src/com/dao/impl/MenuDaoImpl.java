package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.MenuDao;
import com.entity.Menu;
import com.entity.SubMenu;

@Repository("MenuDao")
public class MenuDaoImpl implements MenuDao {

	@Resource(name = "npJdbcHL_Db")
    private NamedParameterJdbcTemplate npJdbcHL_Db;
	
	@Override
	public List<Menu> getMenu(String user_Id) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT FUNC.FUNC_ID, FUNC.FUNC_NAME, FUNC.MENU_ID, FUNC.MENU_NAME"); 
		sb.append(" FROM HL_FUNC FUNC INNER JOIN HL_ROLE_FUNC RF ON FUNC.FUNC_ID = RF.FUNC_ID");
		sb.append(" INNER JOIN HL_USER_ROLE UR ON RF.ROLE_ID = UR.ROLE_ID");
		sb.append(" WHERE UR.USER_ID = :user_Id");
		sb.append(" UNION ALL ");
		sb.append(" SELECT FUNC.FUNC_ID, FUNC.FUNC_NAME, FUNC.MENU_ID, FUNC.MENU_NAME"); 
		sb.append(" FROM HL_FUNC FUNC INNER JOIN HL_USER_FUNC UF ON FUNC.FUNC_ID = UF.FUNC_ID");
		sb.append(" WHERE UF.USER_ID = :user_Id");
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_Id", user_Id);
		
		final List<Menu> list = new ArrayList<Menu>();
		final Map<String,String> menuMap = new HashMap<String,String>();
		npJdbcHL_Db.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Menu menu = new Menu();
				String menu_Id = rs.getString("MENU_ID");
				String menu_Name = rs.getString("MENU_NAME");
				//only set the menu for the first time
				if(menuMap.get(menu_Id) == null){
					menuMap.put(menu_Id, menu_Name);
					menu.setMenuId(menu_Id);
					menu.setMenuName(menu_Name);
					list.add(menu);
				}
			}
		});
		return list;
	}

	
	@Override
	public List<Menu> getSubMenu(String user_Id) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT FUNC.FUNC_ID, FUNC.FUNC_NAME, FUNC.MENU_ID, FUNC.MENU_NAME"); 
		sb.append(" FROM HL_FUNC FUNC INNER JOIN HL_ROLE_FUNC RF ON FUNC.FUNC_ID = RF.FUNC_ID");
		sb.append(" INNER JOIN HL_USER_ROLE UR ON RF.ROLE_ID = UR.ROLE_ID");
		sb.append(" WHERE UR.USER_ID = :user_Id");
		sb.append(" UNION ALL ");
		sb.append(" SELECT FUNC.FUNC_ID, FUNC.FUNC_NAME, FUNC.MENU_ID, FUNC.MENU_NAME"); 
		sb.append(" FROM HL_FUNC FUNC INNER JOIN HL_USER_FUNC UF ON FUNC.FUNC_ID = UF.FUNC_ID");
		sb.append(" WHERE UF.USER_ID = :user_Id");
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("user_Id", user_Id);
		
		final List<Menu> list = new ArrayList<Menu>();
		final Map<String,String> menuMap = new HashMap<String,String>();
		final Map<String,Integer> menuPosition = new HashMap<String,Integer>(); 
		npJdbcHL_Db.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Menu menu = new Menu();
				String menu_Id = rs.getString("MENU_ID");
				String menu_Name = rs.getString("MENU_NAME");
				String func_Id = rs.getString("FUNC_ID");
				String func_Name = rs.getString("FUNC_NAME");
				//only set the menu for the first time
				if(menuMap.get(menu_Id) == null){
					//main menu properties
					menuMap.put(menu_Id, menu_Name);
					menu.setMenuId(menu_Id);
					menu.setMenuName(menu_Name);
					//sub menu properties
					Menu subMenu = new Menu();
					subMenu.setMenuId(func_Id);
					subMenu.setMenuName(func_Name);
					//subMenuList properties
					List<Menu> subMenuList = new ArrayList<Menu>();
					subMenuList.add(subMenu);
					menu.setSubMenu(subMenuList);
					//add menu to return list
					list.add(menu);
					//mark the menu_name position
					menuPosition.put(menu_Name, list.size());
				}else{ 
					//get the menu_name postion
					int position = menuPosition.get(menu_Name);
					//get subMenuList in return list
					Menu subMenu = list.get(position-1);
					List<Menu> subMenuList = subMenu.getSubMenu();
					// funcId not existed
					if(!(subMenu.containFunc(func_Id)) ){
						Menu tempMenu = new Menu();
						tempMenu.setMenuId(func_Id);
						tempMenu.setMenuName(func_Name);
						subMenuList.add(tempMenu);	
					}
					//set the menu properties
					menu.setMenuId(menu_Id);
					menu.setMenuName(menu_Name);
					menu.setSubMenu(subMenuList);
					//reset the Menu in return list
					list.set(position-1, menu);
				}
			}
		});
		return list;
	}
}
