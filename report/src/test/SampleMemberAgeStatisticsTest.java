package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.SampleBuyBackAnalysisByMonthDao;
import com.dhc.dao.SampleMemberAgeStatisticsDao;
import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthQuery;
import com.dhc.entity.SampleBuyBackAnalysisByMonthReturn;
import com.dhc.entity.SampleMemberAgeStatisticsParam;
import com.dhc.entity.SampleMemberAgeStatisticsQuery;
import com.dhc.entity.SampleMemberAgeStatisticsReturn;
import com.dhc.service.SampleMemberAgeStatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SampleMemberAgeStatisticsTest {

	@Resource(name = "SampleMemberAgeStatisticsDao")
	private SampleMemberAgeStatisticsDao sampleMemberAgeStatisticsDao;
	
	@Resource(name = "SampleMemberAgeStatisticsService")
	private SampleMemberAgeStatisticsService sampleMemberAgeStatisticsService;
	
	private SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	@Before
	public void setUp(){
		sampleMemberAgeStatisticsParam = new SampleMemberAgeStatisticsParam();
		sampleMemberAgeStatisticsParam.setStartDate(20150101);
		sampleMemberAgeStatisticsParam.setEndDate(20150201);
		sampleMemberAgeStatisticsParam.setPathWay(0);
	}
	
	@Test
	@Ignore
	public void testGetData(){
		List<SampleMemberAgeStatisticsQuery> li = sampleMemberAgeStatisticsDao.getSampleMemberAgeStatisticsByDate(sampleMemberAgeStatisticsParam);
		for (int i = 0; i < li.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(li.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
//	@Ignore
	public void testTransData(){
		 List<SampleMemberAgeStatisticsReturn> li = sampleMemberAgeStatisticsService.getSampleMemberAgeStatisticsByDate(sampleMemberAgeStatisticsParam);
		 for (int i = 0; i < li.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(li.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
}
