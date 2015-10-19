package com.dhc.entity;

import java.io.Serializable;

public class ShopMappedCity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8905491174131218119L;
	private String dp_code;
	private String dp_name;
	private String dp_prov;
	private String dp_city;
	private String dp_diff;
	
	public String getDp_code() {
		return dp_code;
	}
	public void setDp_code(String dp_code) {
		this.dp_code = dp_code;
	}
	public String getDp_name() {
		return dp_name;
	}
	public void setDp_name(String dp_name) {
		this.dp_name = dp_name;
	}
	public String getDp_prov() {
		return dp_prov;
	}
	public void setDp_prov(String dp_prov) {
		this.dp_prov = dp_prov;
	}
	public String getDp_city() {
		return dp_city;
	}
	public void setDp_city(String dp_city) {
		this.dp_city = dp_city;
	}
	public String getDp_diff() {
		return dp_diff;
	}
	public void setDp_diff(String dp_diff) {
		this.dp_diff = dp_diff;
	}
}
