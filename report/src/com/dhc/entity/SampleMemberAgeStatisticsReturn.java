package com.dhc.entity;

import java.io.Serializable;

public class SampleMemberAgeStatisticsReturn implements Serializable{

	private String age;
	private long below20;
	private long between2130;
	private long between3140;
	private long between4150;
	private long over51;
	private long unknow;
	private long total;
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public long getBelow20() {
		return below20;
	}

	public void setBelow20(long below20) {
		this.below20 = below20;
	}

	public long getBetween2130() {
		return between2130;
	}

	public void setBetween2130(long between2130) {
		this.between2130 = between2130;
	}

	public long getBetween3140() {
		return between3140;
	}

	public void setBetween3140(long between3140) {
		this.between3140 = between3140;
	}

	public long getBetween4150() {
		return between4150;
	}

	public void setBetween4150(long between4150) {
		this.between4150 = between4150;
	}

	public long getOver51() {
		return over51;
	}

	public void setOver51(long over51) {
		this.over51 = over51;
	}

	public long getUnknow() {
		return unknow;
	}

	public void setUnknow(long unknow) {
		this.unknow = unknow;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3831574783590095683L;

}
