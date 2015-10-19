package com.dhc.entity;

import java.io.Serializable;

public class DemoReturn implements Serializable{

	private int row_num;
	private String send_date;
	private String order_no;
	private String receiver;
	private String order_amt;
	private String pay_mode;
	private String dely_gb;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2307054372061759281L;
	
	
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
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
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getOrder_amt() {
		return order_amt;
	}
	public void setOrder_amt(String order_amt) {
		this.order_amt = order_amt;
	}
	public String getPay_mode() {
		return pay_mode;
	}
	public void setPay_mode(String pay_mode) {
		this.pay_mode = pay_mode;
	}
	public String getDely_gb() {
		return dely_gb;
	}
	public void setDely_gb(String dely_gb) {
		this.dely_gb = dely_gb;
	}
	
}
