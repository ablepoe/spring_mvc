package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20151020
 *
 */
public class IkedaReportBReturn implements Serializable{

	
	private String send_date;
	private String order_no;
	private int syslast;
	private String split_amt;
	private String split_dc;
	private String back_order_no;
	private int back_syslast;
	private String back_amt;
	private String back_dc_amt;
	
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public int getSyslast() {
		return syslast;
	}
	public void setSyslast(int syslast) {
		this.syslast = syslast;
	}
	public String getSplit_amt() {
		return split_amt;
	}
	public void setSplit_amt(String split_amt) {
		this.split_amt = split_amt;
	}
	public String getSplit_dc() {
		return split_dc;
	}
	public void setSplit_dc(String split_dc) {
		this.split_dc = split_dc;
	}
	public String getBack_order_no() {
		return back_order_no;
	}
	public void setBack_order_no(String back_order_no) {
		this.back_order_no = back_order_no;
	}
	public int getBack_syslast() {
		return back_syslast;
	}
	public void setBack_syslast(int back_syslast) {
		this.back_syslast = back_syslast;
	}
	public String getBack_amt() {
		return back_amt;
	}
	public void setBack_amt(String back_amt) {
		this.back_amt = back_amt;
	}
	public String getBack_dc_amt() {
		return back_dc_amt;
	}
	public void setBack_dc_amt(String back_dc_amt) {
		this.back_dc_amt = back_dc_amt;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2134223682394877697L;
}
