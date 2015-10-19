package com.dhc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhc.dao.SampleBuyBackAnalysisByMonthDao;
import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthQuery;
import com.dhc.entity.ShopMappedCity;

/**
 * 
 * @author hanliang 20150804
 * -getProvince(),getShopMappedCity() method complete
 * @update hanliang 20150811
 * -remove unused method
 */
@Transactional(value = "transactionManagerCrm")
@Repository("SampleBuyBackAnalysisByMonthDao")
public class SampleBuyBackAnalysisByMonthDaoImpl extends BaseDaoImpl implements SampleBuyBackAnalysisByMonthDao{

	@Override
	public List<String> getProvince() {
		StringBuffer sb = new StringBuffer();
		sb.append("Select dp_prov From t_report_dp_city Group By dp_prov Order By dp_prov");
		final List<String> result = new ArrayList<String>();
		crmJdbcDb.query(sb.toString(), new HashMap<String,String>(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String dp_prov = rs.getString("DP_PROV");
				result.add(dp_prov);
			}
		});
		return result;
	}

	@Override
	public List<ShopMappedCity> getShopMappedCity() {
		StringBuffer sb = new StringBuffer();
		sb.append("Select DP_CODE,DP_NAME,DP_PROV,DP_CITY,DP_DIFF From t_report_dp_city Order By dp_city");
		final List<ShopMappedCity> result = new ArrayList<ShopMappedCity>();
		crmJdbcDb.query(sb.toString(), new HashMap<String,String>(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ShopMappedCity shopMappedCity = new ShopMappedCity();
				
				String dp_code = rs.getString("DP_CODE");
				String dp_name = rs.getString("DP_NAME");
				String dp_prov = rs.getString("DP_PROV");
				String dp_city = rs.getString("DP_CITY");
				String dp_diff = rs.getString("DP_DIFF");
				
				shopMappedCity.setDp_code(dp_code);
				shopMappedCity.setDp_name(dp_name);
				shopMappedCity.setDp_prov(dp_prov);
				shopMappedCity.setDp_city(dp_city);
				shopMappedCity.setDp_diff(dp_diff);
				
				result.add(shopMappedCity);
			}
		});
		return result;
	}

	@Override
	public List<SampleBuyBackAnalysisByMonthQuery> getSampleBuyBackAnalysisByMonth(
			SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam, List<String> provinceList) {
		Map<String,String> paramMap = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();

		sb.append(" Select to_char(A.insert_date,'yyyymm') As sy_date,C.dp_prov,C.dp_city,to_char(B.order_date,'yyyymm') As first_date,Count(A.cust_no) As sy_count,");
		sb.append(" Count(B.cust_no) As order_count,Sum(B.order_amt) As order_amt");
		sb.append(" From tsamplereqm A,t_report_order_first B,t_report_dp_city C");
		sb.append(" Where A.cust_no = B.cust_no(+) And substr(A.insert_id,1,3) = C.dp_code(+)");
		switch(sampleBuyBackAnalysisByMonthParam.getPathWay()){
		case 0: sb.append(" And ((A.sample_type = '40' And A.send_yn = '1' And A.reject_flag <> '1') Or (A.sample_type = '50' And A.reject_flag <> '1'))");
			break;
		case 1: sb.append(" And ((A.sample_type = '40' And A.send_yn = '1' And A.reject_flag <> '1'))");
			break;
		case 2: sb.append(" And ((A.sample_type = '50' And A.reject_flag <> '1'))");
			break;
		case 3: sb.append(" And ((A.sample_type = '50' And A.reject_flag <> '1') And length(A.media_code) = 3)");
			break;
		case 4: sb.append(" And ((A.sample_type = '50' And A.reject_flag <> '1') And length(A.media_code) <> 3)");
			break;
		}
		sb.append(" And A.insert_date Between to_date(:startMonth,'yyyymmdd') And add_months(to_date(:endMonth,'yyyymmdd'),1)");
		sb.append(" And C.dp_prov = :dp_prov");
		if(sampleBuyBackAnalysisByMonthParam.getCity() != ""){
			sb.append(" And C.dp_city like");
			sb.append(" :city");
			paramMap.put("city", "%"+sampleBuyBackAnalysisByMonthParam.getCity()+"%");
		}
		sb.append(" Group By to_char(A.insert_date,'yyyymm'),C.dp_prov,C.dp_city,to_char(B.order_date,'yyyymm')");
		sb.append(" Order By to_char(A.insert_date,'yyyymm'),C.dp_prov,C.dp_city,to_char(B.order_date,'yyyymm')");
		
		paramMap.put("startMonth", sampleBuyBackAnalysisByMonthParam.getStartMonth()+"01");
		paramMap.put("endMonth", sampleBuyBackAnalysisByMonthParam.getEndMonth()+"01");
		paramMap.put("dp_prov", provinceList.get(Integer.parseInt(sampleBuyBackAnalysisByMonthParam.getProvince()))+"");
		
		final List<SampleBuyBackAnalysisByMonthQuery> result = new ArrayList<SampleBuyBackAnalysisByMonthQuery>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SampleBuyBackAnalysisByMonthQuery sampleBuyBackAnalysisByMonthQuery =  new SampleBuyBackAnalysisByMonthQuery();
				int sy_date = rs.getInt("SY_DATE");
				String dp_prov = rs.getString("DP_PROV");
				String dp_city = rs.getString("DP_CITY");
				int first_date = rs.getInt("FIRST_DATE");
				long sy_count = rs.getLong("SY_COUNT");
				long order_count = rs.getLong("ORDER_COUNT");
				long order_amt = rs.getLong("ORDER_AMT");
				
				sampleBuyBackAnalysisByMonthQuery.setSy_date(sy_date);
				sampleBuyBackAnalysisByMonthQuery.setDp_prov(dp_prov);
				sampleBuyBackAnalysisByMonthQuery.setDp_city(dp_city);
				sampleBuyBackAnalysisByMonthQuery.setFirst_date(first_date);
				sampleBuyBackAnalysisByMonthQuery.setSy_count(sy_count);
				sampleBuyBackAnalysisByMonthQuery.setOrder_count(order_count);
				sampleBuyBackAnalysisByMonthQuery.setOrder_amt(order_amt);
				
				result.add(sampleBuyBackAnalysisByMonthQuery);
			}
		});
		return result;
	}
	
}
