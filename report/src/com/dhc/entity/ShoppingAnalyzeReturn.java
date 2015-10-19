package com.dhc.entity;

import java.io.Serializable;

public class ShoppingAnalyzeReturn implements Serializable{

	private String goods_code;
	private String order_qty;
	
	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}

	public String getOrder_qty() {
		return order_qty;
	}

	public void setOrder_qty(String order_qty) {
		this.order_qty = order_qty;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3473255399177073874L;
}
