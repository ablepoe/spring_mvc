package com.dhc.dao;

import java.util.List;

import com.dhc.entity.ShoppingAnalyzeParam;
import com.dhc.entity.ShoppingAnalyzeReturn;

/**
 * 
 * @author hanliang 20150811
 * -
 *
 */
public interface ShoppingAnalyzeDao {

	/**
	 * 
	 * @param shoppingAnalyzeParam
	 * @return
	 */
	public List<ShoppingAnalyzeReturn> firstShoppingAnalyze(ShoppingAnalyzeParam shoppingAnalyzeParam);
	
	/**
	 * turncate table
	 * @return
	 */
	public int truncateTempTable();
	
	/**
	 * create temp table
	 * @param shoppingAnalyzeParam
	 * @return
	 */
	public int createTempTable(ShoppingAnalyzeParam shoppingAnalyzeParam);
	
	/**
	 * 
	 * @return
	 */
	public List<ShoppingAnalyzeReturn> secondShoppingAnalyze();
}
