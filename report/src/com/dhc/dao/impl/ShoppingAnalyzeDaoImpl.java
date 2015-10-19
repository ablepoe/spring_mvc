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

import com.dhc.dao.ShoppingAnalyzeDao;
import com.dhc.entity.ShoppingAnalyzeParam;
import com.dhc.entity.ShoppingAnalyzeReturn;

/**
 * 
 * @author hanliang 20150811
 *
 */
@Transactional(value = "transactionManagerCrm")
@Repository("ShoppingAnalyzeDao")
public class ShoppingAnalyzeDaoImpl extends BaseDaoImpl implements ShoppingAnalyzeDao{

	@Override
	public List<ShoppingAnalyzeReturn> firstShoppingAnalyze(ShoppingAnalyzeParam shoppingAnalyzeParam) {
		StringBuffer sb = new StringBuffer();
		sb.append("Select C.goods_code,Sum(C.order_qty) ORDER_QTY From ");
		sb.append(" (");
		sb.append(" Select cust_no,Min(order_no) As m_order"); 
		sb.append(" From t_report_order_analysis ");
		sb.append(" Where order_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd')");
		sb.append(" Group By cust_no");
		sb.append(" ) A");
		sb.append(" Inner Join torderm B On A.m_order = B.order_no");
		sb.append(" Inner Join tordergoods C On A.m_order = C.order_no");
		sb.append(" Group By C.goods_code");
		sb.append(" Order By Sum(C.order_qty) Desc");

		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", shoppingAnalyzeParam.getStartDate()+"");
		paramMap.put("endDate", shoppingAnalyzeParam.getEndDate()+"");
		final List<ShoppingAnalyzeReturn> result = new ArrayList<ShoppingAnalyzeReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ShoppingAnalyzeReturn shoppingAnalyzeReturn =  new ShoppingAnalyzeReturn();
				String goods_code = rs.getString("GOODS_CODE");
				String order_qty = rs.getString("ORDER_QTY");
				shoppingAnalyzeReturn.setGoods_code(goods_code);
				shoppingAnalyzeReturn.setOrder_qty(order_qty);
				result.add(shoppingAnalyzeReturn);
			}
		});
		return result;
	}

	@Override
	public int truncateTempTable() {
		StringBuffer sb = new StringBuffer();
		sb.append(" Truncate Table t_report_two_buy");
		int result = crmJdbcDb.update(sb.toString(), new HashMap<String,String>());
		return result;
	}

	@Override
	public int createTempTable(ShoppingAnalyzeParam shoppingAnalyzeParam) {
		StringBuffer sb = new StringBuffer();
		sb.append("Insert Into t_report_two_buy"); 
		sb.append(" Select A.cust_no,Min(A.order_no) As m_order From"); 
		sb.append(" (");
		sb.append(" Select A.cust_no,A.order_no From t_report_order_analysis A");
		sb.append(" Left Join"); 
		sb.append(" (");
		sb.append(" Select cust_no,Min(order_no) As m_order"); 
		sb.append(" From t_report_order_analysis"); 
		sb.append(" Where order_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd')");
		sb.append(" Group By cust_no");
		sb.append(" ) B On A.cust_no = B.cust_no And A.order_no = B.m_order");     
		sb.append(" Where A.order_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd') And B.cust_no Is Null");
		sb.append(" ) A");
		sb.append(" Inner Join"); 
		sb.append(" (");
		sb.append(" Select cust_no,Min(order_no) As m_order"); 
		sb.append(" From t_report_order_analysis"); 
		sb.append(" Where order_date Between to_date(:startDate,'yyyymmdd') And to_date(:endDate,'yyyymmdd')");
		sb.append(" Group By cust_no");
		sb.append(" ) B On A.cust_no = B.cust_no");
		sb.append(" Group By A.cust_no");

		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", shoppingAnalyzeParam.getStartDate()+"");
		paramMap.put("endDate", shoppingAnalyzeParam.getEndDate()+"");
		int result = crmJdbcDb.update(sb.toString(), paramMap);
		return result;
	}

	@Override
	public List<ShoppingAnalyzeReturn> secondShoppingAnalyze() {
		StringBuffer sb = new StringBuffer();
		sb.append("Select C.goods_code,Sum(C.order_qty) ORDER_QTY From t_report_two_buy A");
		sb.append(" Inner Join torderm B On A.order_no = B.order_no");
		sb.append(" Inner Join tordergoods C On A.order_no = C.order_no");
		sb.append(" Group By C.goods_code");
		sb.append(" Order By Sum(C.order_qty) Desc");
		final List<ShoppingAnalyzeReturn> result = new ArrayList<ShoppingAnalyzeReturn>();
		crmJdbcDb.query(sb.toString(), new HashMap<String,String>(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ShoppingAnalyzeReturn shoppingAnalyzeReturn =  new ShoppingAnalyzeReturn();
				String goods_code = rs.getString("GOODS_CODE");
				String order_qty = rs.getString("ORDER_QTY");
				shoppingAnalyzeReturn.setGoods_code(goods_code);
				shoppingAnalyzeReturn.setOrder_qty(order_qty);
				result.add(shoppingAnalyzeReturn);
			}
		});
		return result;
	}
	
}
