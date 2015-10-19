package com.dhc.service;

import java.util.List;

import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthReturn;
import com.dhc.entity.ShopMappedCity;

/**
 * 
 * @author hanliang 20150804
 * -getProvince(),getShopMappedCity() mehtod complete
 * @update hanliang 20150811
 * -getSampleBuyBackAnalysisByMonth complete
 */
public interface SampleBuyBackAnalysisByMonthService {

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
	public List<SampleBuyBackAnalysisByMonthReturn> getSampleBuyBackAnalysisByMonth(SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam, List<String> provinceList);
	
	/**
	 * get MonthDuration
	 * @param startMonth
	 * @return MonthBetween list
	 */
	public List<Integer> getMonthDuration(String startMonth, String endMonth);
}
