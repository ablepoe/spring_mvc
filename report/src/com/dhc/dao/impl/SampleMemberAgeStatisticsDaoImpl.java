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

import com.dhc.dao.SampleMemberAgeStatisticsDao;
import com.dhc.entity.SampleMemberAgeStatisticsParam;
import com.dhc.entity.SampleMemberAgeStatisticsQuery;

/**
 * 
 * @author hanliang 20150814
 *
 */
@Transactional(value = "transactionManagerCrm")
@Repository("SampleMemberAgeStatisticsDao")
public class SampleMemberAgeStatisticsDaoImpl extends BaseDaoImpl implements SampleMemberAgeStatisticsDao{

	@Override
	public List<SampleMemberAgeStatisticsQuery> getSampleMemberAgeStatisticsByDate(
			SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam) {
		StringBuffer sb = new StringBuffer();
		sb.append(" Select substr(B.birthday,1,4) As b_day,count(A.cust_no) as sy_count From tsamplereqm A,tcustomer B");
		sb.append(" Where A.cust_no = B.cust_no");
		switch(sampleMemberAgeStatisticsParam.getPathWay()){
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
		default:break;
		}
		sb.append(" And ((A.sample_type = '40' And A.send_yn = '1' And A.reject_flag <> '1') Or (A.sample_type = '50' And A.reject_flag <> '1'))");
		sb.append(" And A.insert_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd')");
		sb.append(" Group By substr(B.birthday,1,4)");
		sb.append(" Order By substr(B.birthday,1,4)");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", sampleMemberAgeStatisticsParam.getStartDate()+"");
		paramMap.put("endDate", sampleMemberAgeStatisticsParam.getEndDate()+"");
		final List<SampleMemberAgeStatisticsQuery> result = new ArrayList<SampleMemberAgeStatisticsQuery>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SampleMemberAgeStatisticsQuery sampleMemberAgeStatisticsParamQuery = new SampleMemberAgeStatisticsQuery();
				String b_day = rs.getString("B_DAY");
				int sy_count = rs.getInt("SY_COUNT");
				try{
					b_day = b_day.trim();
					if(b_day.isEmpty()){
						sampleMemberAgeStatisticsParamQuery.setB_day(0f);
					}else{
						sampleMemberAgeStatisticsParamQuery.setB_day(Float.parseFloat(b_day));	
					}
					sampleMemberAgeStatisticsParamQuery.setSy_count(sy_count);
				}catch (Exception e){
					sampleMemberAgeStatisticsParamQuery.setB_day(0f);
					sampleMemberAgeStatisticsParamQuery.setSy_count(sy_count);
				}
				result.add(sampleMemberAgeStatisticsParamQuery);
			}
		});
		return result;
	}
}
