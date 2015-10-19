package com.dhc.entity;

import java.io.Serializable;

public class HighFrequencySamplingGoodsMemberAnalysisParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220146605816287020L;

	private int startDate;
	private int endDate;
	private int goods_code;
	private int repurchase_count;
	
	public int getGoods_code() {
		return goods_code;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public int getRepurchase_count() {
		return repurchase_count;
	}
	public void setRepurchase_count(int repurchase_count) {
		this.repurchase_count = repurchase_count;
	}

}
