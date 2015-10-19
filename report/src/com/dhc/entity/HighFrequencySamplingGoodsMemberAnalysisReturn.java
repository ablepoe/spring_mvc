package com.dhc.entity;

import java.io.Serializable;
import java.util.List;

public class HighFrequencySamplingGoodsMemberAnalysisReturn implements Serializable{

	private int goods_code;
	private List<Integer> results;
	
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public List<Integer> getResults() {
		return results;
	}
	public void setResults(List<Integer> results) {
		this.results = results;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -7546893904743496351L;
}
