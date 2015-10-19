package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20150826
 *
 */
public class MobileClientOrderQueryReturn implements Serializable{

	private String order_no;
	private String order_date;
	private String send_date;
	private String cust_no;
	private String quest_amt;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getQuest_amt() {
		return quest_amt;
	}
	public void setQuest_amt(String quest_amt) {
		this.quest_amt = quest_amt;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397680560988602458L;

}
