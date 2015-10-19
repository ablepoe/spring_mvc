package com.dhc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dhc.common.CommonUtil;
import com.dhc.dao.DemoDao;
import com.dhc.entity.DemoParam;
import com.dhc.entity.DemoReturn;

/**
 * 
 * @author hanliang 20150702
 *
 */
@Repository("DemoDao")
public class DemoDaoImpl extends BaseDaoImpl implements DemoDao{
	
	@Override
	public List<DemoReturn> getDemo(DemoParam demoParam) {
		
//		String sql = "select SEND_DATE,ORDER_NO,RECEIVER,ORDER_AMT,PAY_MODE,DELY_GB from TORDERBILL where ROWNUM<100";
		
//		String sql = "SELECT * FROM (SELECT ROWNUM NUM,SEND_DATE,ORDER_NO,RECEIVER,ORDER_AMT,PAY_MODE,DELY_GB FROM TORDERBILL) T  where T.NUM > :min AND T.NUM < :MAX";\
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (SELECT ROWNUM NUM,SEND_DATE,ORDER_NO,RECEIVER,ORDER_AMT,PAY_MODE,DELY_GB FROM TORDERBILL) T ");
		sb.append("WHERE ").append(CommonUtil.getRowFilterString(demoParam.getPageInfo().getPageNumber(), demoParam.getPageInfo().getPageSize(), "T.NUM"));
//		Map<String,Integer> map = new HashMap<String,Integer>();
		final List<DemoReturn> result = new ArrayList<DemoReturn>();
		crmJdbcDb.query(sb.toString(), new HashMap<String,String>(), new RowCallbackHandler() {
//		demoJdbcDb.query(sb.toString(), new HashMap<String,String>(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				DemoReturn demo = new DemoReturn();
				int row_num = rs.getInt("NUM");
				String send_date = rs.getString("SEND_DATE");
				String order_no = rs.getString("ORDER_NO");
				String receiver = rs.getString("RECEIVER");
				String order_amt = rs.getString("ORDER_AMT");
				String pay_mode = rs.getString("PAY_MODE");
				String dely_gb = rs.getString("DELY_GB");
				demo.setRow_num(row_num);
				demo.setSend_date(send_date);
				demo.setOrder_no(order_no);
				demo.setReceiver(receiver);
				demo.setOrder_amt(order_amt);
				demo.setPay_mode(pay_mode);
				demo.setDely_gb(dely_gb);
				result.add(demo);
			}
		});
		return result;
	}

	@Override
	public List<DemoReturn> getDemoList(DemoParam demoParam) {
		
		String sql = "select SEND_DATE,ORDER_NO,RECEIVER,ORDER_AMT,PAY_MODE,DELY_GB from TORDERBILL where ROWNUM<10000";
		if(demoParam.getPathways().equals("71")){
			
		}else{
			sql = "select SEND_DATE,ORDER_NO,RECEIVER,ORDER_AMT,PAY_MODE,DELY_GB from TORDERBILL where ROWNUM<10";
		}
		final List<DemoReturn> result = new ArrayList<DemoReturn>();
		crmJdbcDb.query(sql, new HashMap<String,String>(), new RowCallbackHandler() {
//		demoJdbcDb.query(sql, new HashMap<String,String>(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				DemoReturn demo = new DemoReturn();
				String send_date = rs.getString("SEND_DATE");
				String order_no = rs.getString("ORDER_NO");
				String receiver = rs.getString("RECEIVER");
				String order_amt = rs.getString("ORDER_AMT");
				String pay_mode = rs.getString("PAY_MODE");
				String dely_gb = rs.getString("DELY_GB");
				demo.setSend_date(send_date);
				demo.setOrder_no(order_no);
				demo.setReceiver(receiver);
				demo.setOrder_amt(order_amt);
				demo.setPay_mode(pay_mode);
				demo.setDely_gb(dely_gb);
				result.add(demo);
			}
		});
		return result;
	}

	@Override
	public int getDemoSize(DemoParam demoParam) {
		String sql = "select count(1) from TORDERBILL where ROWNUM<102";
		int result = crmJdbcDb.queryForInt(sql, new HashMap<String,String>());
//		int result = demoJdbcDb.queryForInt(sql, new HashMap<String,String>());
		return result;
	}

}
