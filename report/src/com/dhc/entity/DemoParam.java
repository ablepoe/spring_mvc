package com.dhc.entity;

import java.io.Serializable;

public class DemoParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -374630318986063568L;
	
	private String pathways;
	
	private PageInfo pageInfo;
	
	public String getPathways() {
		return pathways;
	}
	public void setPathways(String pathways) {
		this.pathways = pathways;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
