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
import com.dhc.dao.IkedaReportBDao;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;
import com.dhc.entity.IkedaReportBParam;
import com.dhc.entity.IkedaReportBReturn;

/**
 * 
 * @author hanliang 20151020
 * @update hanliang 20151130
 * -replace sql with new
 */
@Repository("IkedaReportBDao")
public class IkedaReportBDaoImpl extends BaseDaoImpl implements IkedaReportBDao {
	
	@Override
	public List<IkedaReportBReturn> getIkedaReportB(
			IkedaReportBParam ikedaReportBParam) {
		StringBuffer sb = new StringBuffer();
		sb.append(" Select A.send_date,Count(Distinct A.order_no),Sum(A.syslast),Sum(A.split_amt),Sum(A.split_dc),");
		sb.append(" Count(Distinct B.order_no),Nvl(Sum(B.syslast),0),");
		sb.append(" Sum(Case When B.order_no Is Not Null Then B.syslast*(A.SPLIT_AMT/NVL(B.SYSLAST,1)) Else 0 End),");
		sb.append(" Sum(Case When B.order_no Is Not Null Then B.syslast*(A.split_dc/NVL(B.SYSLAST,1)) Else 0 End)");
		sb.append(" From torderdt_split A,tclaimdt B"); 
		sb.append(" Where A.order_no = B.order_no(+) And A.goods_code = B.goods_code(+) And length(A.goods_code) = 9");
		sb.append(" And A.send_date >= :startDate And A.send_date <= to_char(Sysdate,'yyyymmdd')");
		sb.append(" Group By A.send_date");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", ikedaReportBParam.getStartDate()+"");
		final List<IkedaReportBReturn> result = new ArrayList<IkedaReportBReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String send_date = rs.getString(1);
				String order_no = rs.getString(2);
				int syslast = rs.getInt(3);
				String split_amt = rs.getString(4);
				String split_dc = rs.getString(5);
				String back_order_no = rs.getString(6);
				int back_syslast = rs.getInt(7);
				String back_amt = rs.getString(8);
				String back_dc_amt = rs.getString(9);
				
				IkedaReportBReturn ikedaReportBReturn = new IkedaReportBReturn();
				ikedaReportBReturn.setSend_date(send_date);
				ikedaReportBReturn.setOrder_no(order_no);
				ikedaReportBReturn.setSyslast(syslast);
				ikedaReportBReturn.setSplit_amt(split_amt);
				ikedaReportBReturn.setSplit_dc(split_dc);
				ikedaReportBReturn.setBack_order_no(back_order_no);
				ikedaReportBReturn.setBack_syslast(back_syslast);
				ikedaReportBReturn.setBack_amt(back_amt);
				ikedaReportBReturn.setBack_dc_amt(back_dc_amt);
				
				result.add(ikedaReportBReturn);
			}
		});
		return result;
	}
}
