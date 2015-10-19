package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20151010
 *
 */
public class IkedaReportAParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3874784650834163790L;

	private int startDate;
	private int endDate;
	private String sortName;
	private String sortOrder;
	
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
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
