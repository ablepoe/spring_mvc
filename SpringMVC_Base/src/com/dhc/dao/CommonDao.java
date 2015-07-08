package com.dhc.dao;

import java.util.List;

import com.dhc.entity.MenuItem;

/**
 * 
 * @author hanliang 20150617
 * -common interface
 *
 */
public interface CommonDao {

	/**
	 * get menu
	 * @param userId
	 * @return
	 */
	public List<MenuItem> getMenuByUserId(String userId);
	
}
