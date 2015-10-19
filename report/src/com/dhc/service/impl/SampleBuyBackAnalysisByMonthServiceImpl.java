package com.dhc.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.SampleBuyBackAnalysisByMonthDao;
import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthQuery;
import com.dhc.entity.SampleBuyBackAnalysisByMonthReturn;
import com.dhc.entity.ShopMappedCity;
import com.dhc.service.SampleBuyBackAnalysisByMonthService;

/**
 * 
 * @author hanliang 20150804
 * -getProvince(),getShopMappedCity() method complete
 * @update hanliang 20150805
 * -add getMonthDuration
 * @update hanliang 20150810
 * -add getSampleBuyBackAnalysisByMonthReturn & getTurnRate
 * @update hanliang 20150811
 * -rebuild code
 */
@Service("SampleBuyBackAnalysisByMonthService")
public class SampleBuyBackAnalysisByMonthServiceImpl implements SampleBuyBackAnalysisByMonthService{

	@Resource(name="SampleBuyBackAnalysisByMonthDao")
	private SampleBuyBackAnalysisByMonthDao sampleBuyBackAnalysisByMonthDao;
	
	@Override
	public List<String> getProvince() {
		return sampleBuyBackAnalysisByMonthDao.getProvince();
	}

	@Override
	public List<ShopMappedCity> getShopMappedCity() {
		return sampleBuyBackAnalysisByMonthDao.getShopMappedCity();
	}

	@Override
	public List<SampleBuyBackAnalysisByMonthReturn> getSampleBuyBackAnalysisByMonth(
			SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam, List<String> provinceList) {
			//get data query
			List<SampleBuyBackAnalysisByMonthQuery> sampleBuyBackAnalysisByMonthQueryList = sampleBuyBackAnalysisByMonthDao.getSampleBuyBackAnalysisByMonth(sampleBuyBackAnalysisByMonthParam, provinceList);
			//format query to return
			List<SampleBuyBackAnalysisByMonthReturn> sampleBuyBackAnalysisByMonthReturnList = getSampleBuyBackAnalysisByMonthReturn(sampleBuyBackAnalysisByMonthQueryList,sampleBuyBackAnalysisByMonthParam);
		return sampleBuyBackAnalysisByMonthReturnList;
	}

	@Override
	public List<Integer> getMonthDuration(String startMonth,String endMonth) {
		List<Integer> monthList = new ArrayList<Integer>();
		
		int start_year = Integer.parseInt(startMonth.substring(0, 4) );
		int start_month = Integer.parseInt(startMonth.substring(4) );
		//set start time
		Calendar cal = Calendar.getInstance();
		cal.set(start_year, start_month-1, 01);
		//set end time
		Calendar cal2 = Calendar.getInstance();
//		int end_year = Integer.parseInt(endMonth.substring(0, 4) );
//		int end_month = Integer.parseInt(endMonth.substring(4) );
//		cal2.set(end_year, end_month-1, 01);
		cal2.set(start_year, start_month+10, 01);
		
		monthList.add(Integer.parseInt(getCalendar(cal.getTime())));
		while(cal2.compareTo(cal) > 0){
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
			String month = getCalendar(cal.getTime());
			monthList.add(Integer.parseInt(month));
		}
		return monthList;
	}

	/**
	 * date format
	 * @param date
	 * @return
	 */
	private String getCalendar(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String currentTime = sdf.format(date);
		return currentTime;
	}
	
	/**
	 * dao result format
	 * @param sampleBuyBackAnalysisByMonthQueryList
	 * @return
	 */
	private List<SampleBuyBackAnalysisByMonthReturn> getSampleBuyBackAnalysisByMonthReturn(List<SampleBuyBackAnalysisByMonthQuery> sampleBuyBackAnalysisByMonthQueryList, SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam){
		//dispatch data by group 分组数据
		List<List<SampleBuyBackAnalysisByMonthQuery>> dataList = dispatchQuery(sampleBuyBackAnalysisByMonthQueryList);
		//data rebuild 重整数据
		List<SampleBuyBackAnalysisByMonthReturn> result = setOtherProperties(dataList,sampleBuyBackAnalysisByMonthParam);
		return result;
	}
	
	/**
	 * date format
	 * @param firstCount
	 * @param firstAmt
	 * @return
	 */
	private double getTurnRate(Double firstCount, Double firstAmt){
		double firstTurn = 0.00;
		if(firstAmt != 0){
			double firstCountD = firstCount;
			double firstAmtD = firstAmt;
			firstTurn = Math.round(firstCountD/firstAmtD * 10000);
			firstTurn = firstTurn/100;
		}
		return firstTurn;
	}
	
	/**
	 * dispath query by gruop
	 * @param sampleBuyBackAnalysisByMonthQueryList
	 * @return
	 */
	private List<List<SampleBuyBackAnalysisByMonthQuery>> dispatchQuery(List<SampleBuyBackAnalysisByMonthQuery> sampleBuyBackAnalysisByMonthQueryList){
		int sy_date = 0;
		String dp_city = null;
		List<SampleBuyBackAnalysisByMonthQuery> innerLi = new ArrayList<SampleBuyBackAnalysisByMonthQuery>();
		List<List<SampleBuyBackAnalysisByMonthQuery>> dataList = new ArrayList<List<SampleBuyBackAnalysisByMonthQuery>>();
		
		for (int i = 0; i < sampleBuyBackAnalysisByMonthQueryList.size(); i++) {
			try {
				if(sy_date == 0 && dp_city==null){
					//init value 第一次，初始值
					sy_date = sampleBuyBackAnalysisByMonthQueryList.get(i).getSy_date();
					dp_city = sampleBuyBackAnalysisByMonthQueryList.get(i).getDp_city();
				}
				if(sy_date == sampleBuyBackAnalysisByMonthQueryList.get(i).getSy_date() && dp_city.equals(sampleBuyBackAnalysisByMonthQueryList.get(i).getDp_city()) ){
					innerLi.add(sampleBuyBackAnalysisByMonthQueryList.get(i));
				}else{
					dataList.add(innerLi);
					//not same list 重置
					innerLi = new ArrayList<SampleBuyBackAnalysisByMonthQuery>();
					innerLi.add(sampleBuyBackAnalysisByMonthQueryList.get(i));
					sy_date = sampleBuyBackAnalysisByMonthQueryList.get(i).getSy_date();
					dp_city = sampleBuyBackAnalysisByMonthQueryList.get(i).getDp_city();
				}
				
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		dataList.add(innerLi);
		return dataList;
	}
	
	/**
	 * data rebuild
	 * @param dataList
	 * @return
	 */
	private List<SampleBuyBackAnalysisByMonthReturn> setOtherProperties(List<List<SampleBuyBackAnalysisByMonthQuery>> dataList, SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam){
		//定义返回集合
		List<SampleBuyBackAnalysisByMonthReturn> result = new ArrayList<SampleBuyBackAnalysisByMonthReturn>();
		
		for (int k = 0; k < dataList.size(); k++) {
			List<SampleBuyBackAnalysisByMonthQuery> _value = dataList.get(k);
			//no data 查无数据
			if(_value.size() != 0){
				//object transform 转换成输出对象
				SampleBuyBackAnalysisByMonthReturn sampleBuyBackAnalysisByMonthReturn = new SampleBuyBackAnalysisByMonthReturn();
				sampleBuyBackAnalysisByMonthReturn.setSy_date(_value.get(0).getSy_date() + "");
				sampleBuyBackAnalysisByMonthReturn.setProvince(_value.get(0).getDp_prov() + "");
				sampleBuyBackAnalysisByMonthReturn.setCity(_value.get(0).getDp_city() + "");
				long sy_count = 0;
				long firstCount = 0;
				long firstAmt = 0;
				for (int i = 0; i < _value.size(); i++) {
					sy_count += _value.get(i).getSy_count();
					if(_value.get(i).getFirst_date() < _value.get(i).getSy_date()){
						firstCount += _value.get(i).getOrder_count();
						firstAmt += _value.get(i).getOrder_amt();
					}
				}
				sampleBuyBackAnalysisByMonthReturn.setSy_count(sy_count+"");
				sampleBuyBackAnalysisByMonthReturn.setFirstCount(firstCount+"");
				sampleBuyBackAnalysisByMonthReturn.setFirstAmt(firstAmt+"");
				
				double firstTurn = getTurnRate((double)firstCount, (double)sy_count);
				sampleBuyBackAnalysisByMonthReturn.setFirstTurn(firstTurn +"%");
				
				//single column value 输出其他属性
				double turnRate;
				//check time 
				String time = sampleBuyBackAnalysisByMonthParam.getStartMonth()+"";
				int in_month = Integer.parseInt(time.substring(time.length()-2, time.length()));
				int diff = in_month -1;
				int in_year = Integer.parseInt(time.substring(0,time.length()-2));
				for (int i = 0; i < _value.size(); i++) {
					if(_value.get(i).getSy_date() <= _value.get(i).getFirst_date() && _value.get(i).getFirst_date() != 0){
							String fd = _value.get(i).getFirst_date() +"";
							int first_month = Integer.parseInt(fd.substring(fd.length()-2, fd.length()));
							int first_year = Integer.parseInt(fd.substring(0, fd.length()-2));
							int condition;
							//time interval
							if(first_year > in_year){
								condition = (first_year-in_year)*12+first_month-diff;
							}else{
								condition = first_month-diff;
							}
							switch(condition){
							case 1: sampleBuyBackAnalysisByMonthReturn.set_1firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_1firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_1fristTurn(turnRate+ "%");
								break;
							case 2: sampleBuyBackAnalysisByMonthReturn.set_2firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_2firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_2fristTurn(turnRate+ "%");
								break;
							case 3: sampleBuyBackAnalysisByMonthReturn.set_3firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_3firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_3fristTurn(turnRate+ "%");
								break;
							case 4: sampleBuyBackAnalysisByMonthReturn.set_4firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_4firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_4fristTurn(turnRate+ "%");
								break;
							case 5: sampleBuyBackAnalysisByMonthReturn.set_5firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_5firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_5fristTurn(turnRate+ "%");
								break;
							case 6: sampleBuyBackAnalysisByMonthReturn.set_6firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_6firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_6fristTurn(turnRate+ "%");
								break;
							case 7: sampleBuyBackAnalysisByMonthReturn.set_7firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_7firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_7fristTurn(turnRate+ "%");
								break;
							case 8: sampleBuyBackAnalysisByMonthReturn.set_8firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_8firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_8fristTurn(turnRate+ "%");
								break;
							case 9: sampleBuyBackAnalysisByMonthReturn.set_9firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_9firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_9fristTurn(turnRate+ "%");
								break;
							case 10:sampleBuyBackAnalysisByMonthReturn.set_10firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_10firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_10fristTurn(turnRate+ "%");
								break;
							case 11:sampleBuyBackAnalysisByMonthReturn.set_11firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_11firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_11fristTurn(turnRate+ "%");
								break;
							case 12:sampleBuyBackAnalysisByMonthReturn.set_12firstCount(_value.get(i).getOrder_count() + "");
									sampleBuyBackAnalysisByMonthReturn.set_12firstAmt(_value.get(i).getOrder_amt() + "");
									turnRate = getTurnRate((double)_value.get(i).getOrder_count(), (double)sy_count);
									sampleBuyBackAnalysisByMonthReturn.set_12fristTurn(turnRate+ "%");
								break;
							default:break;
							}
					}
				}
				result.add(sampleBuyBackAnalysisByMonthReturn);
			}
		}
		return result;
	}
}
