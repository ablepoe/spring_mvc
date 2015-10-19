package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang
 * -crm return data
 */
public class CrmReturn implements Serializable{
	
	private String goods_code;
	private String goods_name;
	private String memb_no;
	private String order_date;
	private String order_media;
	private String order_no;
	private String pre_price;
	private String pre_sale_price;
	private String qty;
	private String real_sale_price;
	private String sale_price;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8023779949254654820L;
	
	public String getOrder_media() {
		return order_media;
	}
	public void setOrder_media(String order_media) {
		this.order_media = order_media;
	}
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
	public String getMemb_no() {
		return memb_no;
	}
	public void setMemb_no(String memb_no) {
		this.memb_no = memb_no;
	}
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
	public String getSale_price() {
		return sale_price;
	}
	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getReal_sale_price() {
		return real_sale_price;
	}
	public void setReal_sale_price(String real_sale_price) {
		this.real_sale_price = real_sale_price;
	}
	public String getPre_sale_price() {
		return pre_sale_price;
	}
	public void setPre_sale_price(String pre_sale_price) {
		this.pre_sale_price = pre_sale_price;
	}
	public String getPre_price() {
		return pre_price;
	}
	public void setPre_price(String pre_price) {
		this.pre_price = pre_price;
	}
}
