package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.ShoppingAnalyzeDao;
import com.dhc.entity.ShoppingAnalyzeParam;
import com.dhc.entity.ShoppingAnalyzeReturn;
import com.dhc.service.ShoppingAnalyzeService;

/**
 * 
 * @author hanliang 20150811
 *
 */
@Service("ShoppingAnalyzeService")
public class ShoppingAnalyzeServiceImpl implements ShoppingAnalyzeService{

	@Resource(name="ShoppingAnalyzeDao")
	private ShoppingAnalyzeDao shoppingAnalyzeDao;
	
	@Override
	public List<ShoppingAnalyzeReturn> firstShoppingAnalyze(
			ShoppingAnalyzeParam shoppingAnalyzeParam) {
		return shoppingAnalyzeDao.firstShoppingAnalyze(shoppingAnalyzeParam);
	}

	@Override
	public int truncateTempTable() {
		return shoppingAnalyzeDao.truncateTempTable();
	}

	@Override
	public int createTempTable(ShoppingAnalyzeParam shoppingAnalyzeParam) {
		return shoppingAnalyzeDao.createTempTable(shoppingAnalyzeParam);
	}

	@Override
	public List<ShoppingAnalyzeReturn> secondShoppingAnalyze() {
		return shoppingAnalyzeDao.secondShoppingAnalyze();
	}

}
