package com.dhc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhc.dao.MobileClientOrderQueryDao;
import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.entity.MobileClientOrderQueryReturn;

/**
 * 
 * @author hanliang 20150826
 *
 */
@Transactional(value = "transactionManagerCrm")
@Repository("MobileClientOrderQueryDao")
public class MoblieClientOrderQueryDaoImpl extends BaseDaoImpl implements MobileClientOrderQueryDao{
	
	private Logger log = Logger.getLogger(MoblieClientOrderQueryDaoImpl.class);
	
	@Override
	public List<MobileClientOrderQueryReturn> mobileClientOrderQuery(
			MobileClientOrderQueryParam moblieClientOrderQueryParam) {
		StringBuffer sb = new StringBuffer();
		sb.append(" Select A.order_no,A.order_date,C.send_date,A.cust_no,Sum(B.quest_amt) As quest_amt ");
		sb.append(" From torderm A,torderreceipts B,torderbill C");
		sb.append(" Where A.order_no = B.order_no And A.order_no = C.order_no(+)");
		sb.append(" And A.order_date Between to_date(:startDate,'yyyymmdd') AND TO_DATE (:endDate,'yyyymmdd hh24:mi:ss')");
		sb.append(" And A.order_media = '71'");
		sb.append(" And B.settle_gb Not In ('21','50','51','05')"); 
		sb.append(" Group By A.order_no,A.order_date,C.send_date,A.cust_no");
		sb.append(" Order By A.order_date");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", moblieClientOrderQueryParam.getStartDate() + "");
		paramMap.put("endDate", moblieClientOrderQueryParam.getEndDate() + "235959");
		final List<MobileClientOrderQueryReturn> result = new ArrayList<MobileClientOrderQueryReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				MobileClientOrderQueryReturn mobileClientOrderQueryReturn = new MobileClientOrderQueryReturn();
					
				String order_no = rs.getString("ORDER_NO");
				String order_date = rs.getString("ORDER_DATE");
				String send_date = rs.getString("SEND_DATE");
				String cust_no = rs.getString("CUST_NO");
				String quest_amt = rs.getString("QUEST_AMT");
				
				SimpleDateFormat full_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat half_sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				if(order_date != null){
					try {
						date = full_sdf.parse(order_date);
					} catch (ParseException e) {
						log.error("Error occured in mobileClientOrderQuery order_date", e);
					}
					order_date = full_sdf.format(date);
				}
				if(send_date != null){
					try {
						date = half_sdf.parse(send_date);
					} catch (ParseException e) {
						log.error("Error occured in mobileClientOrderQuery send_date", e);
					}
					send_date = half_sdf.format(date);	
				}
				mobileClientOrderQueryReturn.setOrder_no(order_no);
				mobileClientOrderQueryReturn.setOrder_date(order_date);
				mobileClientOrderQueryReturn.setSend_date(send_date);
				mobileClientOrderQueryReturn.setCust_no(cust_no);
				mobileClientOrderQueryReturn.setQuest_amt(quest_amt);
				result.add(mobileClientOrderQueryReturn);
			}
		});
		return result;
	}
	
}
