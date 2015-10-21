package com.dhc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.dhc.dao.IkedaReportCDao;
import com.dhc.entity.IkedaReportCParam;
import com.dhc.entity.IkedaReportCReturn;


/**
 * 
 * @author hanliang
 *
 */
 @Repository("IkedaReportCDao")
public class IkedaReportCDaoImpl extends BaseDaoImpl implements IkedaReportCDao{

	@Override
	public List<IkedaReportCReturn> getIkedaReportCData(
			IkedaReportCParam ikedaReportParam) {
		StringBuffer sb = new StringBuffer();
		sb.append(" ");
		sb.append(" Select ORDER_NO,GOODS_CODE,SYSLAST,SYSLAST_AMT,GOODS_AMT,DC_AMT,SPLIT_DC,SPLIT_AMT,SEND_DATE"); 
		sb.append(" From torderdt_split Where length(goods_code) = 9 And send_date >= :startDate And send_date <= to_char(Sysdate,'yyyymmdd') Order By order_no,goods_code");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", ikedaReportParam.getStartDate()+"");
		final List<IkedaReportCReturn> result = new ArrayList<IkedaReportCReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String order_no = rs.getString("ORDER_NO");
				String goods_code = rs.getString("GOODS_CODE");
				int syslast = rs.getInt("SYSLAST");
				int syslast_amt = rs.getInt("SYSLAST_AMT");
				int goods_amt = rs.getInt("GOODS_AMT");
				int dc_amt = rs.getInt("DC_AMT");
				Double split_dc = rs.getDouble("SPLIT_DC");
				Double split_amt = rs.getDouble("SPLIT_AMT");
				String send_date = rs.getString("SEND_DATE");
				
				IkedaReportCReturn ikedaReportCReturn = new IkedaReportCReturn();
				ikedaReportCReturn.setOrder_no(order_no);
				ikedaReportCReturn.setGoods_code(goods_code);
				ikedaReportCReturn.setSyslast(syslast);
				ikedaReportCReturn.setSyslast_amt(syslast_amt);
				ikedaReportCReturn.setGoods_amt(goods_amt);
				ikedaReportCReturn.setDc_amt(dc_amt);
				ikedaReportCReturn.setSplit_dc(split_dc);
				ikedaReportCReturn.setSplit_amt(split_amt);
				ikedaReportCReturn.setSend_date(send_date);
				result.add(ikedaReportCReturn);
			}
		});
		return result;
	}

	 
	 
}
