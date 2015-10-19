package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20150811
 *
 */
public class ShoppingAnalyzeParam implements Serializable{

	private int startDate;
	private int endDate;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 229837557031738766L;

	
	
}
