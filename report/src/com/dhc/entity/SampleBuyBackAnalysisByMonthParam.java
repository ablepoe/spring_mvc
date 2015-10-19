package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20150805
 * 
 */
public class SampleBuyBackAnalysisByMonthParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4804640418679520025L;

	private int startMonth;
	private int endMonth;
	private int pathWay;
	private String province;
	private String city;

	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	public int getPathWay() {
		return pathWay;
	}
	public void setPathWay(int pathWay) {
		this.pathWay = pathWay;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
