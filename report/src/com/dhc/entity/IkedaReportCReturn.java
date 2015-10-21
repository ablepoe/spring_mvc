package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20151021
 *
 */
public class IkedaReportCReturn implements Serializable{

	private String order_no;
	private String goods_code;
	private int syslast;
	private int syslast_amt;
	private int goods_amt;
	private int dc_amt;
	private Double split_dc;
	private Double split_amt;
	private String send_date;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public int getSyslast() {
		return syslast;
	}
	public void setSyslast(int syslast) {
		this.syslast = syslast;
	}
	public int getSyslast_amt() {
		return syslast_amt;
	}
	public void setSyslast_amt(int syslast_amt) {
		this.syslast_amt = syslast_amt;
	}
	public int getGoods_amt() {
		return goods_amt;
	}
	public void setGoods_amt(int goods_amt) {
		this.goods_amt = goods_amt;
	}
	public int getDc_amt() {
		return dc_amt;
	}
	public void setDc_amt(int dc_amt) {
		this.dc_amt = dc_amt;
	}
	public Double getSplit_dc() {
		return split_dc;
	}
	public void setSplit_dc(Double split_dc) {
		this.split_dc = split_dc;
	}
	public Double getSplit_amt() {
		return split_amt;
	}
	public void setSplit_amt(Double split_amt) {
		this.split_amt = split_amt;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7642693033854985921L;
}
