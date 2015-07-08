package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.CommonDao;
import com.dhc.entity.MenuItem;
import com.dhc.service.CommonService;

/**
 * 
 * @author hanliang 20160617
 * -commmonServiceImpl with commonDao interface 
 *
 */
@Service("CommonService")
public class CommonServiceImpl implements CommonService{

	@Resource(name="CommonDao")
	private CommonDao commonDao;
	
	
	@Override
	public List<MenuItem> getMenuByUserId(String userId) {
		return commonDao.getMenuByUserId(userId);
	}

}
