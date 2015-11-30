package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20151021
 * @update hanliang 20151026
 * -add extra properties
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
	private String cust_name;
	private int birthday;
	private String city_name;
	private String address;
	private int cust_level_id;
	private int memb_no;
	private String tel;
	private String back_amt;
	private String back_dc_amt;
	
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
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCust_level_id() {
		return cust_level_id;
	}
	public void setCust_level_id(int cust_level_id) {
		this.cust_level_id = cust_level_id;
	}
	public int getMemb_no() {
		return memb_no;
	}
	public void setMemb_no(int memb_no) {
		this.memb_no = memb_no;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	private static final long serialVersionUID = 7642693033854985921L;

}
