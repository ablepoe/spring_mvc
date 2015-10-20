package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20151020
 *
 */
public class IkedaReportBParam implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -274743173512618608L;
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
