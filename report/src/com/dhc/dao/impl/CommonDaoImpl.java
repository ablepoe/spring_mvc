package com.dhc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhc.common.Common;
import com.dhc.dao.CommonDao;
import com.dhc.entity.MenuItem;

/**
 * 
 * @author hanliang 20150617
 * -get user menu&function Id
 * 
 */
@Transactional(value="transactionManager")
@Repository("CommonDao")
public class CommonDaoImpl extends BaseDaoImpl implements CommonDao {
	
	@Override
	public List<MenuItem> getMenuByUserId(int userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (  ");
		sb.append("SELECT FST.MENU_ID FST_MENU_ID, FST.MENU_NAME FST_MENU_NAME,  SND.MENU_ID SND_MENU_ID, SND.MENU_NAME SND_MENU_NAME, FUNC.FUNC_ID, FUNC.FUNC_NAME, FUNC.REAL_PATH, ");
		sb.append("FST.SORT_NO FST_SORT_NO, SND.SORT_NO SND_SORT_NO, FUNC.SORT_NO FUNC_SORT_NO  ");
		sb.append("FROM REPORTFORM_FUNC FUNC  ");
		sb.append("INNER JOIN REPORTFORM_MENU SND ON FUNC.MENU_ID = SND.MENU_ID ");
		sb.append("INNER JOIN REPORTFORM_MENU FST ON SND.PARENT_MENU_ID = FST.MENU_ID ");
		sb.append("WHERE FUNC.FUNC_ID = '99999' AND FUNC.FUNC_TYPE = :funcType ");
		sb.append("UNION ALL ");
		sb.append("SELECT FST.MENU_ID FST_MENU_ID, FST.MENU_NAME FST_MENU_NAME,  SND.MENU_ID SND_MENU_ID, SND.MENU_NAME SND_MENU_NAME, FUNC.FUNC_ID, FUNC.FUNC_NAME, ");
		sb.append("FUNC.REAL_PATH,   FST.SORT_NO FST_SORT_NO, SND.SORT_NO SND_SORT_NO, FUNC.SORT_NO FUNC_SORT_NO ");
		sb.append("FROM REPORTFORM_FUNC FUNC ");
		sb.append("INNER JOIN REPORTFORM_MENU SND ON FUNC.MENU_ID = SND.MENU_ID ");
		sb.append("INNER JOIN REPORTFORM_MENU FST ON SND.PARENT_MENU_ID = FST.MENU_ID ");
		sb.append("INNER JOIN REPORTFORM_USER_FUNC USRFUNC ON FUNC.FUNC_ID = USRFUNC.FUNC_ID AND USER_ID = :userId ");
		sb.append("UNION ALL ");
		sb.append("SELECT FST.MENU_ID FST_MENU_ID, FST.MENU_NAME FST_MENU_NAME,  SND.MENU_ID SND_MENU_ID, SND.MENU_NAME SND_MENU_NAME, FUNC.FUNC_ID, FUNC.FUNC_NAME, ");
		sb.append("FUNC.REAL_PATH,  FST.SORT_NO FST_SORT_NO, SND.SORT_NO SND_SORT_NO, FUNC.SORT_NO FUNC_SORT_NO ");
		sb.append("FROM REPORTFORM_USER USR ");
		sb.append("INNER JOIN REPORTFORM_USER_ROLE USRR ON USR.USER_ID = USRR.USER_ID ");
		sb.append("INNER JOIN REPORTFORM_ROLE_FUNC RFUNC ON USRR.ROLE_ID = RFUNC.ROLE_ID ");
		sb.append("INNER JOIN REPORTFORM_FUNC FUNC ON FUNC.FUNC_ID = RFUNC.FUNC_ID ");
		sb.append("INNER JOIN REPORTFORM_MENU SND ON FUNC.MENU_ID = SND.MENU_ID ");
		sb.append("INNER JOIN REPORTFORM_MENU FST ON SND.PARENT_MENU_ID = FST.MENU_ID ");
		sb.append("WHERE USR.USER_ID = :userId  ) TMP  GROUP BY TMP.FST_MENU_ID, TMP.FST_MENU_NAME,   TMP.SND_MENU_ID, TMP.SND_MENU_NAME, TMP.FUNC_ID, TMP.FUNC_NAME, TMP.REAL_PATH, ");
		sb.append("TMP.FST_SORT_NO, TMP.SND_SORT_NO, FUNC_SORT_NO ");
		sb.append("ORDER BY TMP.FST_SORT_NO, TMP.SND_SORT_NO, FUNC_SORT_NO ");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("funcType", Common.FUNCTYPE);
		paramMap.put("userId", userId+"");
		
		
		final String[] menuId = {"", "", ""};
        final int[] menuItemIdx = {0, 0, 0};
    	
        final List<MenuItem> menuList = new ArrayList<MenuItem>(); 
        crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
//        npJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
            	MenuItem fstMenuItem = null;
            	if (!menuId[0].equals(rs.getString("FST_MENU_ID"))) {
            		fstMenuItem = new MenuItem();
            		fstMenuItem.setItemId(rs.getString("FST_MENU_ID"));
            		fstMenuItem.setItemName(rs.getString("FST_MENU_NAME"));
            		fstMenuItem.setRealPath("");
            		menuList.add(fstMenuItem);
            		menuItemIdx[0]++;
            		menuItemIdx[1] = 0;
            	} else {
            		fstMenuItem = menuList.get(menuItemIdx[0] - 1);
            	}
            	
            	MenuItem sndMenuItem = null;
            	if (!menuId[1].equals(rs.getString("SND_MENU_ID"))) {
            		sndMenuItem = new MenuItem();
            		sndMenuItem.setItemId(rs.getString("SND_MENU_ID"));
            		sndMenuItem.setItemName(rs.getString("SND_MENU_NAME"));
            		sndMenuItem.setRealPath("");
            		fstMenuItem.getSubMenuItem().add(sndMenuItem);
            		menuItemIdx[1]++;
            		menuItemIdx[2] = 0;
            	} else {
            		sndMenuItem = fstMenuItem.getSubMenuItem().get(menuItemIdx[1] - 1);
            	}
            	
            	MenuItem funcItem = null;
            	if (!menuId[2].equals(rs.getString("FUNC_ID"))) {
            		funcItem = new MenuItem();
            		funcItem.setItemId(rs.getString("FUNC_ID"));
            		funcItem.setItemName(rs.getString("FUNC_NAME"));
            		funcItem.setRealPath(rs.getString("REAL_PATH"));
            		sndMenuItem.getSubMenuItem().add(funcItem);
            		menuItemIdx[2]++;
            	}
            	
            	menuId[0] = rs.getString("FST_MENU_ID");
            	menuId[1] = rs.getString("SND_MENU_ID");
            	menuId[2] = rs.getString("FUNC_ID");
            }
        });
        
        return menuList;
	}
}
