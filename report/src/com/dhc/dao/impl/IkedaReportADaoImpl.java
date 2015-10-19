package com.dhc.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.dhc.dao.IkedaReportADao;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;

/**
 * 
 * @author hanliang 20151010
 * @update hanliang 20151014
 * -change send_date from 20151001 to 20150930
 * -change sql in getIkedaReportA
 */
@Repository("IkedaReportADao")
public class IkedaReportADaoImpl extends BaseDaoImpl implements IkedaReportADao {
	
	@Override
	public List<IkedaReportAReturn> getIkedaReportA(
			IkedaReportAParam ikedaReportAParam) {
		StringBuffer sb = new StringBuffer();
		sb.append(" Select C.goods_code,B.goods_name,Nvl(A.A_syslast,0) As A_syslast,Nvl(A.A_split_amt,0) As A_split_amt,C.C_syslast,C.C_split_amt,D.D_aqty,E.min_send_date From"); 
		sb.append(" (");
		sb.append(" Select substr(goods_code,1,5) As goods_code,Sum(syslast) As C_syslast,Sum(split_amt) As C_split_amt");
		sb.append(" From torderdt_split");  
		sb.append(" Where length(goods_code) = 9 And send_date >= '20150930'"); 
		sb.append(" Group By substr(goods_code,1,5) Order By substr(goods_code,1,5)");
		sb.append(" ) C,");
		sb.append(" (Select substr(goods_code,1,5) As goods_code,goods_name,Count(1) From tgoods Where length(goods_code) = 9 Group By substr(goods_code,1,5),goods_name) B,");
		sb.append(" (");
		sb.append(" Select substr(goods_code,1,5) As goods_code,Sum(syslast) As A_syslast,Sum(split_amt) As A_split_amt");
		sb.append(" From torderdt_split");  
		sb.append(" Where length(goods_code) = 9 And send_date >= :startDate And send_date <= to_char(Sysdate,'yyyymmdd')");
		sb.append(" Group By substr(goods_code,1,5) Order By substr(goods_code,1,5)");
		sb.append(" ) A,");
		sb.append(" (Select substr(goods_code,1,5) As goods_code,Sum(aqty) As D_aqty From tstock Where wh_code = '001' And length(goods_code) = 9 Group By substr(goods_code,1,5)) D,");
		sb.append(" (Select substr(goods_code,1,5) As goods_code,Min(send_date) As min_send_date From torderdt_split Where length(goods_code) = 9 Group By substr(goods_code,1,5)) E");
		sb.append(" Where C.goods_code = B.goods_code And C.goods_code = A.goods_code(+) And C.goods_code = D.goods_code(+) And C.goods_code = E.goods_code");
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", ikedaReportAParam.getStartDate()+"");
		final int currentDate = ikedaReportAParam.getEndDate();
		final List<IkedaReportAReturn> result = new ArrayList<IkedaReportAReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				
				String goods_code = rs.getString("GOODS_CODE");
				String goods_name = rs.getString("GOODS_NAME");
				int middle_syslast = rs.getInt("A_SYSLAST");
				String middle_split_amt = rs.getString("A_SPLIT_AMT");
				int total_syslast = rs.getInt("C_SYSLAST");
				String total_split_amt = rs.getString("C_SPLIT_AMT");
				int stock = rs.getInt("D_AQTY");
				int min_send_date = rs.getInt("MIN_SEND_DATE");
				
				//data format and to %
				double total_turnrate_d = (double)total_syslast/(total_syslast+stock) ;
				total_turnrate_d = Math.round(total_turnrate_d * 10000 ) / 100.0; 
				String total_turnrate = format(total_turnrate_d);
				
				//data format
				long dateBetween = dateBetween(min_send_date, currentDate);
				double denominator = dateBetween+1;
				double goods_turn_duration_d = precision(stock/ (total_syslast/denominator));
				String goods_turn_duration = format(goods_turn_duration_d);
				
				IkedaReportAReturn ikedaReportAReturn = new IkedaReportAReturn();
				ikedaReportAReturn.setGoods_code(goods_code);
				ikedaReportAReturn.setGoods_name(goods_name);
				ikedaReportAReturn.setMiddle_syslast(middle_syslast);
				ikedaReportAReturn.setMiddle_split_amt(middle_split_amt);
				ikedaReportAReturn.setTotal_syslast(total_syslast);
				ikedaReportAReturn.setTotal_split_amt(total_split_amt);
				ikedaReportAReturn.setTotal_turnrate(total_turnrate);
				ikedaReportAReturn.setStock(stock);
				ikedaReportAReturn.setGoods_turn_duration(goods_turn_duration);
				result.add(ikedaReportAReturn);
			}
		});
		return result;
	}

	private double precision(double d){
		BigDecimal bg = new BigDecimal(d);  
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	
	private String format(double d){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");  
		return df.format(d);
	}
	
	private long dateBetween(int dateStart, int dateEnd){
		String start = String.valueOf(dateStart);
		Calendar startCal = Calendar.getInstance();
		startCal.set(Integer.parseInt(start.substring(0, 4)), Integer.parseInt(start.substring(4, 6)), Integer.parseInt(start.substring(6)));
		
		String end = String.valueOf(dateEnd);
		Calendar endCal = Calendar.getInstance();
		endCal.set(Integer.parseInt(end.substring(0, 4)), Integer.parseInt(end.substring(4, 6)), Integer.parseInt(end.substring(6)));
		
		long between_days = (endCal.getTimeInMillis()-startCal.getTimeInMillis())/(1000*3600*24);
		return between_days; 
	}
}
