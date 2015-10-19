package com.dhc.dao;

import java.util.List;

import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthQuery;
import com.dhc.entity.ShopMappedCity;

/**
 * 
 * @author hanliang 20150804
 * -getProvince(),getShopMappedCity() mehtod complete
 * @update hanliang 20150811
 * -remove unused method
 */
public interface SampleBuyBackAnalysisByMonthDao {

	/**
	 * get province
	 * @return province list
	 */
	public List<String> getProvince();
	
	/**
	 * get shopMappedCity
	 * @return shopMappedCity list
	 */
	public List<ShopMappedCity> getShopMappedCity();
	
	/**
	 * get SampleBuyBackAnalysisByMonth
	 * @param sampleBuyBackAnalysisByMonthParam
	 * @return SampleBuyBackAnalysisByMonthReturn list
	 */
	public List<SampleBuyBackAnalysisByMonthQuery> getSampleBuyBackAnalysisByMonth(SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam, List<String> provinceList);
}
