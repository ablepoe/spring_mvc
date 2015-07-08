package com.dhc.service;

import java.util.List;

import com.dhc.entity.MenuItem;

/**
 * 
 * @author hanliang 20160617
 * -commonService interface
 *
 */
public interface CommonService {
	
	/**
	 * get menu
	 * @param userId
	 * @return
	 */
	public List<MenuItem> getMenuByUserId(String userId);
	
}
