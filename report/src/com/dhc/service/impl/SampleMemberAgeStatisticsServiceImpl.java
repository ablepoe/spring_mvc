package com.dhc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.SampleMemberAgeStatisticsDao;
import com.dhc.entity.SampleMemberAgeStatisticsParam;
import com.dhc.entity.SampleMemberAgeStatisticsQuery;
import com.dhc.entity.SampleMemberAgeStatisticsReturn;
import com.dhc.service.SampleMemberAgeStatisticsService;

/**
 * 
 * @author hanliang 20150814
 *
 */
@Service("SampleMemberAgeStatisticsService")
public class SampleMemberAgeStatisticsServiceImpl implements SampleMemberAgeStatisticsService{

	@Resource(name = "SampleMemberAgeStatisticsDao")
	private SampleMemberAgeStatisticsDao sampleMemberAgeStatisticsDao;
	
	@Override
	public List<SampleMemberAgeStatisticsReturn> getSampleMemberAgeStatisticsByDate(
			SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam) {
		//get data query
		List<SampleMemberAgeStatisticsQuery> sampleMemberAgeStatisticsQueryList = sampleMemberAgeStatisticsDao.getSampleMemberAgeStatisticsByDate(sampleMemberAgeStatisticsParam);
		//format query to return
		List<SampleMemberAgeStatisticsReturn> sampleMemberAgeStatisticsReturnList = getSampleMemberAgeStatisticsByDateReturn(sampleMemberAgeStatisticsQueryList,sampleMemberAgeStatisticsParam);
		return sampleMemberAgeStatisticsReturnList;
	}
	
	private List<SampleMemberAgeStatisticsReturn> getSampleMemberAgeStatisticsByDateReturn(List<SampleMemberAgeStatisticsQuery> sampleMemberAgeStatisticsQueryList,SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam){
		
		SampleMemberAgeStatisticsReturn sampleMemberAgeStatisticsReturn = new SampleMemberAgeStatisticsReturn();
		//define compare param
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		float below20Max = year-10;
		float below20Min = year-20;
		float between2130Max = year-21;
		float between2130Min = year-30;
		float between3140Max = year-31;
		float between3140Min = year-40;
		float between4150Max = year-41;
		float between4150Min = year-50;
		float over51Max = year -51;
		float over51Min = year -80;
		//define return param
		long below20 = 0;
		long between2130 = 0;
		long between3140 = 0;
		long between4150 = 0;
		long over51 = 0;
		long unknow = 0;
		long total = 0;
		//check data
		for (int i = 0; i < sampleMemberAgeStatisticsQueryList.size(); i++) {
			if(sampleMemberAgeStatisticsQueryList.get(i).getB_day() >= below20Min && sampleMemberAgeStatisticsQueryList.get(i).getB_day()<= below20Max){
				below20 += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
			}else if(sampleMemberAgeStatisticsQueryList.get(i).getB_day() >= between2130Min && sampleMemberAgeStatisticsQueryList.get(i).getB_day() <= between2130Max){
				between2130 += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
			}else if(sampleMemberAgeStatisticsQueryList.get(i).getB_day() >= between3140Min && sampleMemberAgeStatisticsQueryList.get(i).getB_day() <= between3140Max){
				between3140 += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
			}else if(sampleMemberAgeStatisticsQueryList.get(i).getB_day() >= between4150Min && sampleMemberAgeStatisticsQueryList.get(i).getB_day() <= between4150Max){
				between4150 += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
			}else if(sampleMemberAgeStatisticsQueryList.get(i).getB_day() >= over51Min && sampleMemberAgeStatisticsQueryList.get(i).getB_day() <= over51Max){
				over51 += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
			}else{
				unknow += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
			}
			total += sampleMemberAgeStatisticsQueryList.get(i).getSy_count();
		}
		//set data
		sampleMemberAgeStatisticsReturn.setAge("人数");
		sampleMemberAgeStatisticsReturn.setBelow20(below20);
		sampleMemberAgeStatisticsReturn.setBetween2130(between2130);
		sampleMemberAgeStatisticsReturn.setBetween3140(between3140);
		sampleMemberAgeStatisticsReturn.setBetween4150(between4150);
		sampleMemberAgeStatisticsReturn.setOver51(over51);
		sampleMemberAgeStatisticsReturn.setUnknow(unknow);
		sampleMemberAgeStatisticsReturn.setTotal(total);
		List<SampleMemberAgeStatisticsReturn> result = new ArrayList<SampleMemberAgeStatisticsReturn>();
		result.add(sampleMemberAgeStatisticsReturn);
		return result;
	}
	

}
