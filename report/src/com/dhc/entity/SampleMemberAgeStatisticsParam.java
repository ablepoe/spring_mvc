package com.dhc.entity;

import java.io.Serializable;

public class SampleMemberAgeStatisticsParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1102685710548863518L;

	private int startDate;
	private int endDate;
	private int pathWay;
	
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
	public int getPathWay() {
		return pathWay;
	}
	public void setPathWay(int pathWay) {
		this.pathWay = pathWay;
	}
}
