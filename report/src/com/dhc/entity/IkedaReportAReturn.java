package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20151010
 *
 */
public class IkedaReportAReturn implements Serializable{

	private String goods_code;
	private String goods_name;
	private int middle_syslast;
	private String middle_split_amt;
	private int total_syslast;
	private String total_split_amt;
	private String total_turnrate;
	private int stock;
	private String goods_turn_duration;
	
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getMiddle_syslast() {
		return middle_syslast;
	}
	public void setMiddle_syslast(int middle_syslast) {
		this.middle_syslast = middle_syslast;
	}
	public String getMiddle_split_amt() {
		return middle_split_amt;
	}
	public void setMiddle_split_amt(String middle_split_amt) {
		this.middle_split_amt = middle_split_amt;
	}
	public int getTotal_syslast() {
		return total_syslast;
	}
	public void setTotal_syslast(int total_syslast) {
		this.total_syslast = total_syslast;
	}
	public String getTotal_split_amt() {
		return total_split_amt;
	}
	public void setTotal_split_amt(String total_split_amt) {
		this.total_split_amt = total_split_amt;
	}
	public String getTotal_turnrate() {
		return total_turnrate;
	}
	public void setTotal_turnrate(String total_turnrate) {
		this.total_turnrate = total_turnrate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getGoods_turn_duration() {
		return goods_turn_duration;
	}
	public void setGoods_turn_duration(String goods_turn_duration) {
		this.goods_turn_duration = goods_turn_duration;
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2507472231712630232L;
	
}
