package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20150826
 *
 */
public class MobileClientOrderQueryParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383682068821365580L;

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
	
}
