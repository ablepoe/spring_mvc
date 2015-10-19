package com.dhc.entity;

import java.io.Serializable;

public class SampleBuyBackAnalysisByMonthQuery implements Serializable{

	private int sy_date;
	private String dp_prov;
	private String dp_city;
	private int first_date;
	private long sy_count;
	private long order_count;
	private long order_amt;
	
	public int getSy_date() {
		return sy_date;
	}

	public void setSy_date(int sy_date) {
		this.sy_date = sy_date;
	}

	public String getDp_prov() {
		return dp_prov;
	}

	public void setDp_prov(String dp_prov) {
		this.dp_prov = dp_prov;
	}

	public String getDp_city() {
		return dp_city;
	}

	public void setDp_city(String dp_city) {
		this.dp_city = dp_city;
	}

	public int getFirst_date() {
		return first_date;
	}

	public void setFirst_date(int first_date) {
		this.first_date = first_date;
	}

	public long getSy_count() {
		return sy_count;
	}

	public void setSy_count(long sy_count) {
		this.sy_count = sy_count;
	}

	public long getOrder_count() {
		return order_count;
	}

	public void setOrder_count(long order_count) {
		this.order_count = order_count;
	}

	public long getOrder_amt() {
		return order_amt;
	}

	public void setOrder_amt(long order_amt) {
		this.order_amt = order_amt;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572292354334981395L;

}
