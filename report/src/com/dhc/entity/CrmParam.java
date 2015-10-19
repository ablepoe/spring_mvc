package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang
 * -crm input param
 */
public class CrmParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3990378705296908943L;

	private String pathways;
	private String date_from;
	private String date_to;
	private String order_no;
	private String goods_no;
	private String memb_no;
	private String order_amt_min;
	private String order_amt_max;
	private String template_name;
	
	public String getPathways() {
		return pathways;
	}
	public void setPathways(String pathways) {
		this.pathways = pathways;
	}
	public String getDate_from() {
		return date_from;
	}
	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}
	public String getDate_to() {
		return date_to;
	}
	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	public String getMemb_no() {
		return memb_no;
	}
	public void setMemb_no(String memb_no) {
		this.memb_no = memb_no;
	}
	public String getOrder_amt_min() {
		return order_amt_min;
	}
	public void setOrder_amt_min(String order_amt_min) {
		this.order_amt_min = order_amt_min;
	}
	public String getOrder_amt_max() {
		return order_amt_max;
	}
	public void setOrder_amt_max(String order_amt_max) {
		this.order_amt_max = order_amt_max;
	}
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
}
