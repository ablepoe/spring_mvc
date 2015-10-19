package com.dhc.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dhc.dao.HighFrequencySamplingGoodsMemberAnalysisDao;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisParam;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisReturn;

/**
 * 
 * @author hanliang 20150812
 *
 */
@Repository("HighFrequencySamplingGoodsMemberAnalysisDao")
public class HighFrequencySamplingGoodsMemberAnalysisDaoImpl extends BaseDaoImpl implements HighFrequencySamplingGoodsMemberAnalysisDao{

	@Override
	public HighFrequencySamplingGoodsMemberAnalysisReturn getResults(
			HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam) {
		List<Integer> li = getDetailResults(highFrequencySamplingGoodsMemberAnalysisParam);
		HighFrequencySamplingGoodsMemberAnalysisReturn result = new HighFrequencySamplingGoodsMemberAnalysisReturn();
		result.setGoods_code(highFrequencySamplingGoodsMemberAnalysisParam.getGoods_code());;
		result.setResults(li);
		return result;
	}
	
	private List<Integer> getDetailResults(HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i < highFrequencySamplingGoodsMemberAnalysisParam.getRepurchase_count(); i++) {
			int detail = getSingleResult(highFrequencySamplingGoodsMemberAnalysisParam, i, i+1);
			result.add(detail);
		}
		return result;
	}
	
	private int getSingleResult(HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam, int seq, int seq2){
		StringBuffer sb = new StringBuffer();
		sb.append(" Select trunc(sum(trunc(B.order_date-A.order_date))/count(Distinct A.cust_no)) From ");
		sb.append(" (Select * From t_report_goods_seq Where goods_code = :goods_code And goods_seq = :seq ");
		sb.append(" And order_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd')) A");
		sb.append(" Inner Join (Select * From t_report_goods_seq Where goods_code = :goods_code And goods_seq = :seq2");
		sb.append(" And order_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd')) B On A.cust_no = B.cust_no");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("goods_code", highFrequencySamplingGoodsMemberAnalysisParam.getGoods_code() + "");
		paramMap.put("startDate", highFrequencySamplingGoodsMemberAnalysisParam.getStartDate() +"");
		paramMap.put("endDate", highFrequencySamplingGoodsMemberAnalysisParam.getEndDate() +"");
		paramMap.put("seq", seq+"");
		paramMap.put("seq2", seq2+"");
		int i;
		if(crmJdbcDb.queryForObject(sb.toString(), paramMap, Integer.class) != null){
			i = crmJdbcDb.queryForObject(sb.toString(), paramMap, Integer.class);	
		}else{
			i = 0;
		}
		return i;
	}

}
