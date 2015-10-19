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
import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthQuery;
import com.dhc.entity.SampleBuyBackAnalysisByMonthReturn;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SampleBuyBackAnalysisByMonthTest {

	@Resource(name = "SampleBuyBackAnalysisByMonthDao")
	private SampleBuyBackAnalysisByMonthDao sampleBuyBackAnalysisByMonthDao;
	
	private SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	@Before
	public void setUp(){
		sampleBuyBackAnalysisByMonthParam = new SampleBuyBackAnalysisByMonthParam();
		sampleBuyBackAnalysisByMonthParam.setStartMonth(201501);
		sampleBuyBackAnalysisByMonthParam.setEndMonth(201502);
		sampleBuyBackAnalysisByMonthParam.setPathWay(0);
		sampleBuyBackAnalysisByMonthParam.setProvince("4");
		sampleBuyBackAnalysisByMonthParam.setCity("深");
//		sampleBuyBackAnalysisByMonthParam.setCity("深圳市");
	}
	
	private List<SampleBuyBackAnalysisByMonthQuery> generateData(){
		List<SampleBuyBackAnalysisByMonthQuery> li = new ArrayList<SampleBuyBackAnalysisByMonthQuery>();
		SampleBuyBackAnalysisByMonthQuery sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("东莞市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(1323l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(1323l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(616915l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("东莞市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201502);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(42l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(42l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(18157l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("东莞市");
		sampleBuyBackAnalysisByMonthQuery.setSy_count(3199l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(0l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("佛山市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(200810);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(1l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(5l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(276l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("佛山市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201305);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(1l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(1l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(338l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("佛山市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(442l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(442l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(206154l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("佛山市");
		sampleBuyBackAnalysisByMonthQuery.setSy_count(2297l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(0l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(1761l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(1761l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(866441l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201502);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(47l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(47l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(27085l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201503);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(15l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(15l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(7434l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201504);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(8l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(8l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(4971l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201505);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(10l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(10l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(5448l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201506);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(7l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(7l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(3137l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setFirst_date(201507);
		sampleBuyBackAnalysisByMonthQuery.setSy_count(7l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(7l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_amt(2261l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		sampleBuyBackAnalysisByMonthQuery = new SampleBuyBackAnalysisByMonthQuery();
		sampleBuyBackAnalysisByMonthQuery.setSy_date(201501);
		sampleBuyBackAnalysisByMonthQuery.setDp_prov("广东");
		sampleBuyBackAnalysisByMonthQuery.setDp_city("深圳市");
		sampleBuyBackAnalysisByMonthQuery.setSy_count(1630l);
		sampleBuyBackAnalysisByMonthQuery.setOrder_count(0l);
		li.add(sampleBuyBackAnalysisByMonthQuery);
		
		return li;
	}
	
	@Test
//	@Ignore
	public void testGetSampleBuyBackAnalysisByMonth(){
//		long ts = System.currentTimeMillis();
//		List<SampleBuyBackAnalysisByMonthQuery> result = sampleBuyBackAnalysisByMonthDao.getSampleBuyBackAnalysisByMonth(sampleBuyBackAnalysisByMonthParam);
//		long ts2 = System.currentTimeMillis();
//		System.out.println("ts2-ts :"+(ts2-ts));
//		for (int i = 0; i < result.size(); i++) {
//			try {
//				System.out.println(mapper.writeValueAsString(result.get(i)));
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
		
//		List<SampleBuyBackAnalysisByMonthQuery> li = generateData();
		List<String> provinceList = sampleBuyBackAnalysisByMonthDao.getProvince();
		List<SampleBuyBackAnalysisByMonthQuery> li = sampleBuyBackAnalysisByMonthDao.getSampleBuyBackAnalysisByMonth(sampleBuyBackAnalysisByMonthParam,provinceList);
		int sy_date = 0;
		String dp_city = null;
		String key = null;
		List<SampleBuyBackAnalysisByMonthQuery> innerLi = new ArrayList<SampleBuyBackAnalysisByMonthQuery>();
		Map<String,List<SampleBuyBackAnalysisByMonthQuery>> map = new HashMap<String,List<SampleBuyBackAnalysisByMonthQuery>>();
		List<List<SampleBuyBackAnalysisByMonthQuery>> replaceMap = new ArrayList<List<SampleBuyBackAnalysisByMonthQuery>>();
		//转存对象 至map<string,list>
		//list根据sy_date和dp_city复合做区分
		for (int i = 0; i < li.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(li.get(i)));
				if(sy_date == 0 && dp_city==null){
					//第一次，初始值
					sy_date = li.get(i).getSy_date();
					dp_city = li.get(i).getDp_city();
					key = sy_date+dp_city;
				}
//				System.out.println(key);
				if(sy_date == li.get(i).getSy_date() && dp_city.equals(li.get(i).getDp_city()) ){
//					System.out.println("sy_date true && dp_city true");
					innerLi.add(li.get(i));
				}else{
					map.put(key, innerLi);
					replaceMap.add(innerLi);
					//not same 重置
					innerLi = new ArrayList<SampleBuyBackAnalysisByMonthQuery>();
					innerLi.add(li.get(i));
//					System.out.println("not same");
					sy_date = li.get(i).getSy_date();
					dp_city = li.get(i).getDp_city();
					key = sy_date+dp_city;
				}
				
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		map.put(key, innerLi);
		replaceMap.add(innerLi);
		
		//根据整合好的map开始处理输出数据
		List<SampleBuyBackAnalysisByMonthReturn> result = new ArrayList<SampleBuyBackAnalysisByMonthReturn>();
		Iterator<Entry<String, List<SampleBuyBackAnalysisByMonthQuery>>> it = map.entrySet().iterator();
		
		for (int k = 0; k < replaceMap.size(); k++) {
			List<SampleBuyBackAnalysisByMonthQuery> _value = replaceMap.get(k);
			System.out.println("_value is "+_value.size());
			
			//转换成输出对象
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
			
			//输出其他属性
			double turnRate;
			for (int i = 0; i < _value.size(); i++) {
				if(_value.get(i).getSy_date() <= _value.get(i).getFirst_date() && _value.get(i).getFirst_date() != 0){
						String fd = _value.get(i).getFirst_date() +"";
						int first_date = Integer.parseInt(fd.substring(fd.length()-2, fd.length()));
						switch(first_date){
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
			try {
				System.out.println(mapper.writeValueAsString(sampleBuyBackAnalysisByMonthReturn));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			result.add(sampleBuyBackAnalysisByMonthReturn);
		}
		
		/*while(it.hasNext()){
			Entry<String, List<SampleBuyBackAnalysisByMonthQuery>> entry = it.next();
			String _key = entry.getKey();
			List<SampleBuyBackAnalysisByMonthQuery> _value = entry.getValue();
			System.out.println("_key is "+_key);
			System.out.println("_value is "+_value.size());
			
			//转换成输出对象
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
			
			//输出其他属性
			double turnRate;
			for (int i = 0; i < _value.size(); i++) {
				if(_value.get(i).getSy_date() <= _value.get(i).getFirst_date() && _value.get(i).getFirst_date() != 0){
						String fd = _value.get(i).getFirst_date() +"";
						int first_date = Integer.parseInt(fd.substring(fd.length()-2, fd.length()));
						switch(first_date){
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
			try {
				System.out.println(mapper.writeValueAsString(sampleBuyBackAnalysisByMonthReturn));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			result.add(sampleBuyBackAnalysisByMonthReturn);
		}*/
		
	}
	
	@Test
	@Ignore
	public void turncat(){
		String sql = "Truncate table t_report_two_buy";
//		jdbc.(update)
	}
	
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
}
