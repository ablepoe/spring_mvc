package com.enetity;

import java.io.Serializable;

public class SpecialDeal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026043771673935416L;
	
	private DepartFrom departFrom;
	private ArriveAt ArriveAt;
	private int cost;
	
	public DepartFrom getDepartFrom() {
		return departFrom;
	}
	public void setDepartFrom(DepartFrom departFrom) {
		this.departFrom = departFrom;
	}
	public ArriveAt getArriveAt() {
		return ArriveAt;
	}
	public void setArriveAt(ArriveAt arriveAt) {
		ArriveAt = arriveAt;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
