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
 * @update hanliang
 * -replace sql with new
 */
 @Repository("IkedaReportCDao")
public class IkedaReportCDaoImpl extends BaseDaoImpl implements IkedaReportCDao{

	@Override
	public List<IkedaReportCReturn> getIkedaReportCData(
			IkedaReportCParam ikedaReportParam) {
		StringBuffer sb = new StringBuffer();
		sb.append(" Select A.ORDER_NO,A.GOODS_CODE,A.SYSLAST,A.SYSLAST_AMT,A.GOODS_AMT,A.DC_AMT,A.SPLIT_DC,A.SPLIT_AMT,A.SEND_DATE,");
		sb.append(" C.cust_name,C.birthday,E.city_name,E.city_name||E.dong_name||D.receiver_addr,F.cust_level_id,C.memb_no,NVL(D.Receiver_HP,D.TEL) As TEL");
		sb.append(" From torderdt_split A,torderm B,tcustomer C,treceiver D,tpost E,t_np_cust_level F");
		sb.append(" Where A.order_no = B.order_no And B.cust_no = C.cust_no And B.cust_no = D.cust_no And B.cust_no = F.cust_no(+)");
		sb.append(" And D.default_yn = '1'");
		sb.append(" And Case When D.receiver_post = '999999' Then substr(D.Receiver_Addr,1,6) Else D.Receiver_Post End = E.post_no");
		sb.append(" And Case When D.receiver_post = '999999' Then '001' Else D.Receiver_Post_seq End = E.post_seq"); 
		sb.append(" And length(A.goods_code) = 9 And A.send_date >= :startDate And A.send_date <= to_char(Sysdate,'yyyymmdd')"); 
		sb.append(" Order By A.order_no,A.goods_code");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("startDate", ikedaReportParam.getStartDate()+"");
		final List<IkedaReportCReturn> result = new ArrayList<IkedaReportCReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				try{
					String order_no = rs.getString("ORDER_NO");
					String goods_code = rs.getString("GOODS_CODE");
					int syslast = rs.getInt("SYSLAST");
					int syslast_amt = rs.getInt("SYSLAST_AMT");
					int goods_amt = rs.getInt("GOODS_AMT");
					int dc_amt = rs.getInt("DC_AMT");
					Double split_dc = rs.getDouble("SPLIT_DC");
					Double split_amt = rs.getDouble("SPLIT_AMT");
					String send_date = rs.getString("SEND_DATE");
					String cust_name = rs.getString("CUST_NAME");
					int birthday = rs.getInt("BIRTHDAY");
					String city_name = rs.getString("CITY_NAME");
					String address = rs.getString("E.CITY_NAME||E.DONG_NAME||D.RECEIVER_ADDR");
					int cust_level_id = rs.getInt("CUST_LEVEL_ID");
					int memb_no = rs.getInt("memb_no");
					String tel = rs.getString("tel");
					
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
					ikedaReportCReturn.setCust_name(cust_name);
					ikedaReportCReturn.setBirthday(birthday);
					ikedaReportCReturn.setCity_name(city_name);
					ikedaReportCReturn.setAddress(address);
					ikedaReportCReturn.setCust_level_id(cust_level_id);
					ikedaReportCReturn.setMemb_no(memb_no);
					ikedaReportCReturn.setTel(tel);
					result.add(ikedaReportCReturn);
				} catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		return result;
	}

	 
	 
}
