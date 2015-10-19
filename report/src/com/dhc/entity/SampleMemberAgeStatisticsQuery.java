package com.dhc.entity;

import java.io.Serializable;

public class SampleMemberAgeStatisticsQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6165181235780535477L;

	private float b_day;
	private int sy_count;
	
	public float getB_day() {
		return b_day;
	}
	public void setB_day(float b_day) {
		this.b_day = b_day;
	}
	public int getSy_count() {
		return sy_count;
	}
	public void setSy_count(int sy_count) {
		this.sy_count = sy_count;
	}
}
