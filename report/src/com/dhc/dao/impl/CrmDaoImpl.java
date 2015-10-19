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

import com.dhc.dao.CrmDao;
import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;

/**
 * 
 * @author hanliang
 * -crmData
 */
@Transactional(value = "transactionManagerCrm")
@Repository("CrmDao")
public class CrmDaoImpl extends BaseDaoImpl implements CrmDao{
	
	@Override
	public List<CrmReturn> getCrmDownloadData(CrmParam crmParam){
		
		/*StringBuffer sb = new StringBuffer();
		sb.append("select * from REPORTFORM_func");
		Map<String,String> paramMap = new HashMap<String,String>();
		final List<CrmReturn> result = new ArrayList<CrmReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CrmReturn crmReturn = new CrmReturn();
				crmReturn.setGoods_code(rs.getString("FUNC_ID"));
				crmReturn.setGoods_name(rs.getString("FUNC_NAME"));
				crmReturn.setMemb_no(rs.getString("FUNC_TYPE"));
				crmReturn.setOrder_date(rs.getString("MENU_ID"));
				crmReturn.setOrder_media(rs.getString("REAL_PATH"));
				crmReturn.setOrder_no(rs.getString("SORT_NO"));
				crmReturn.setPre_price(rs.getString("REMARK"));
				crmReturn.setPre_sale_price(rs.getString("FUNC_ID"));
				crmReturn.setQty(rs.getString("FUNC_NAME"));
				crmReturn.setReal_sale_price(rs.getString("FUNC_TYPE"));
				crmReturn.setSale_price(rs.getString("MENU_ID"));
				
				result.add(crmReturn);
			}
		});
		return result;*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ROWNUM row_id,DT.* from ( ");
		sql.append("Select C.order_media order_media,A.order_no order_no,C.order_date order_date,E.memb_no memb_no,B.goods_code goods_code, ");
		sql.append("D.goods_name goods_name,B.sale_price sale_price,(B.order_qty-B.cancel_qty) qty, ");
		sql.append("(B.sale_price-B.dc_amt)*(B.order_qty-B.cancel_qty) real_sale_price,trunc( ((B.sale_price-B.dc_amt)*(B.order_qty-B.cancel_qty))*A.pre_price,2) pre_sale_price,trunc(A.pre_price,2) pre_price ");
		sql.append("From ( ");
		sql.append("Select A.order_no,(A.order_amt/B.sum_price) As pre_price ");
		sql.append("From torderbill A,(Select order_no,Sum((order_qty-cancel_qty)*(sale_price-dc_amt)) As sum_price From tordergoods Group By order_no) B "); 
		sql.append("Where A.order_no = B.order_no ");
		if("".equals(crmParam.getOrder_amt_min()) || crmParam.getOrder_amt_min() == null){
			if(!"".equals(crmParam.getOrder_amt_max()) && crmParam.getOrder_amt_max() != null){
				sql.append("And A.order_amt <= '"+crmParam.getOrder_amt_max()+"' ");
			}
		}else{
			if("".equals(crmParam.getOrder_amt_max()) || crmParam.getOrder_amt_max() == null){
				sql.append("And A.order_amt >= '"+crmParam.getOrder_amt_min()+"' ");
			}else{
				sql.append("And A.order_amt Between '"+crmParam.getOrder_amt_min()+"' And '"+crmParam.getOrder_amt_max()+"' ");
			}
		}
		sql.append(") A, ");
		sql.append("tordergoods B, ");
		sql.append("torderm C, ");
		sql.append("tgoods D, ");
		sql.append("tcustomer E ");
		sql.append("Where A.order_no = B.order_no And A.order_no = C.order_no And B.goods_code = D.goods_code(+) And C.cust_no = E.cust_no ");
		if(!"ALL".equals(crmParam.getPathways()) && crmParam.getPathways()!=null){
			sql.append("And C.order_media = '"+crmParam.getPathways()+"' ");
		}
		sql.append("And C.order_date Between to_date('"+crmParam.getDate_from()+"','yyyymmdd') And to_date('"+crmParam.getDate_to()+"','yyyymmdd') ");
		if(!"".equals(crmParam.getOrder_no()) && crmParam.getOrder_no()!=null){
			sql.append("And A.order_no = '"+crmParam.getOrder_no()+"' ");
		}
		if(!"".equals(crmParam.getGoods_no()) && crmParam.getGoods_no()!=null){
			sql.append(" And B.goods_code = '"+crmParam.getGoods_no()+"' ");
		}
		if(!"".equals(crmParam.getMemb_no()) && crmParam.getMemb_no()!=null){
			sql.append("And E.memb_no = '"+crmParam.getMemb_no()+"' ");
		}
		
		sql.append("Order By A.order_no "); 
		sql.append(" ) DT");
		
		Map<String,String> paramMap = new HashMap<String,String>();
		final List<CrmReturn> result = new ArrayList<CrmReturn>();
		crmJdbcDb.query(sql.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CrmReturn crmReturn = new CrmReturn();
				
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String membNo = rs.getString("memb_no");
				String orderDate = rs.getString("order_date");
				String orderMedia = rs.getString("order_media");
				String orderNo = rs.getString("order_no");
				String prePrice = rs.getString("pre_price");
				String preSalePrice = rs.getString("pre_sale_price");
				String qty = rs.getString("qty");
				String realSalePrice = rs.getString("real_sale_price");
				String salePrice = rs.getString("sale_price");
				
				crmReturn.setGoods_code(goodsCode);
				crmReturn.setGoods_name(goodsName);
				crmReturn.setMemb_no(membNo);
				crmReturn.setOrder_date(orderDate);
				crmReturn.setOrder_media(orderMedia);
				crmReturn.setOrder_no(orderNo);
				crmReturn.setPre_price(prePrice);
				crmReturn.setPre_sale_price(preSalePrice);
				crmReturn.setQty(qty);
				crmReturn.setReal_sale_price(realSalePrice);
				crmReturn.setSale_price(salePrice);
				
				result.add(crmReturn);
				
			}
		});
		return result;
	}
	
	@Override
	public List<CrmReturn> getCrmData(CrmParam crmParam){
		
		/*StringBuffer sb = new StringBuffer();
		sb.append("select * from REPORTFORM_func");
		Map<String,String> paramMap = new HashMap<String,String>();
		final List<CrmReturn> result = new ArrayList<CrmReturn>();
		crmJdbcDb.query(sb.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CrmReturn crmReturn = new CrmReturn();
				crmReturn.setGoods_code(rs.getString("FUNC_ID"));
				crmReturn.setGoods_name(rs.getString("FUNC_NAME"));
				crmReturn.setMemb_no(rs.getString("FUNC_TYPE"));
				crmReturn.setOrder_date(rs.getString("MENU_ID"));
				crmReturn.setOrder_media(rs.getString("REAL_PATH"));
				crmReturn.setOrder_no(rs.getString("SORT_NO"));
				crmReturn.setPre_price(rs.getString("REMARK"));
				crmReturn.setPre_sale_price(rs.getString("FUNC_ID"));
				crmReturn.setQty(rs.getString("FUNC_NAME"));
				crmReturn.setReal_sale_price(rs.getString("FUNC_TYPE"));
				crmReturn.setSale_price(rs.getString("MENU_ID"));
				
				result.add(crmReturn);
			}
		});
		return result;*/
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ROWNUM row_id,DT.* from ( ");
		sql.append("Select C.order_media order_media,A.order_no order_no,C.order_date order_date,E.memb_no memb_no,B.goods_code goods_code, ");
		sql.append("D.goods_name goods_name,B.sale_price sale_price,(B.order_qty-B.cancel_qty) qty, ");
		sql.append("(B.sale_price-B.dc_amt)*(B.order_qty-B.cancel_qty) real_sale_price,trunc( ((B.sale_price-B.dc_amt)*(B.order_qty-B.cancel_qty))*A.pre_price,2) pre_sale_price,trunc(A.pre_price,2) pre_price ");
		sql.append("From ( ");
		sql.append("Select A.order_no,(A.order_amt/B.sum_price) As pre_price ");
		sql.append("From torderbill A,(Select order_no,Sum((order_qty-cancel_qty)*(sale_price-dc_amt)) As sum_price From tordergoods Group By order_no) B "); 
		sql.append("Where A.order_no = B.order_no ");
		if("".equals(crmParam.getOrder_amt_min()) || crmParam.getOrder_amt_min() == null){
			if(!"".equals(crmParam.getOrder_amt_max()) && crmParam.getOrder_amt_max() != null){
				sql.append("And A.order_amt <= '"+crmParam.getOrder_amt_max()+"' ");
			}
		}else{
			if("".equals(crmParam.getOrder_amt_max()) || crmParam.getOrder_amt_max() == null){
				sql.append("And A.order_amt >= '"+crmParam.getOrder_amt_min()+"' ");
			}else{
				sql.append("And A.order_amt Between '"+crmParam.getOrder_amt_min()+"' And '"+crmParam.getOrder_amt_max()+"' ");
			}
		}
		sql.append(") A, ");
		sql.append("tordergoods B, ");
		sql.append("torderm C, ");
		sql.append("tgoods D, ");
		sql.append("tcustomer E ");
		sql.append("Where A.order_no = B.order_no And A.order_no = C.order_no And B.goods_code = D.goods_code(+) And C.cust_no = E.cust_no ");
		if(!"ALL".equals(crmParam.getPathways()) && crmParam.getPathways()!=null){
			sql.append("And C.order_media = '"+crmParam.getPathways()+"' ");
		}
		sql.append("And C.order_date Between to_date('"+crmParam.getDate_from()+"','yyyymmdd') And to_date('"+crmParam.getDate_to()+"','yyyymmdd') ");
		if(!"".equals(crmParam.getOrder_no()) && crmParam.getOrder_no()!=null){
			sql.append("And A.order_no = '"+crmParam.getOrder_no()+"' ");
		}
		if(!"".equals(crmParam.getGoods_no()) && crmParam.getGoods_no()!=null){
			sql.append(" And B.goods_code = '"+crmParam.getGoods_no()+"' ");
		}
		if(!"".equals(crmParam.getMemb_no()) && crmParam.getMemb_no()!=null){
			sql.append("And E.memb_no = '"+crmParam.getMemb_no()+"' ");
		}
		
		sql.append("Order By A.order_no "); 
		sql.append(" ) DT");
		
		Map<String,String> paramMap = new HashMap<String,String>();
		final List<CrmReturn> result = new ArrayList<CrmReturn>();
		crmJdbcDb.query(sql.toString(), paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CrmReturn crmReturn = new CrmReturn();
				
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String membNo = rs.getString("memb_no");
				String orderDate = rs.getString("order_date");
				String orderMedia = rs.getString("order_media");
				String orderNo = rs.getString("order_no");
				String prePrice = rs.getString("pre_price");
				String preSalePrice = rs.getString("pre_sale_price");
				String qty = rs.getString("qty");
				String realSalePrice = rs.getString("real_sale_price");
				String salePrice = rs.getString("sale_price");
				
				crmReturn.setGoods_code(goodsCode);
				crmReturn.setGoods_name(goodsName);
				crmReturn.setMemb_no(membNo);
				crmReturn.setOrder_date(orderDate);
				crmReturn.setOrder_media(orderMedia);
				crmReturn.setOrder_no(orderNo);
				crmReturn.setPre_price(prePrice);
				crmReturn.setPre_sale_price(preSalePrice);
				crmReturn.setQty(qty);
				crmReturn.setReal_sale_price(realSalePrice);
				crmReturn.setSale_price(salePrice);
				
				result.add(crmReturn);
			}
		});
		return result;
	}
}
