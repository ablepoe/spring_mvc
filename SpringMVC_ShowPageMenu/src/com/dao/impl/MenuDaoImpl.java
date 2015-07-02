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
		final Map<String,String> valueMap = new HashMap<String,String>();
		npJdbcHL_Db.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Menu menu = new Menu();
				String menu_Id = rs.getString("MENU_ID");
				String menu_Name = rs.getString("MENU_NAME");
				//only set the menu for the first time
				if(valueMap.get(menu_Id) == null){
					valueMap.put(menu_Id, menu_Name);
					menu.setMenuId(menu_Id);
					menu.setMenuName(menu_Name);
					list.add(menu);
				}
			}
		});
		return list;
	}

}
