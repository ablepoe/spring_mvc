package com.dhc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author hanliang 20160617
 * -menu entity 
 *
 */
public class MenuItem implements Serializable  {

	private static final long serialVersionUID = 5167188343647820298L;

	private String itemId;
	private String itemName;
	private String realPath;
	private List<MenuItem> subMenuItem = new ArrayList<MenuItem>();
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public List<MenuItem> getSubMenuItem() {
		return subMenuItem;
	}
	public void setSubMenuItem(List<MenuItem> subMenuItem) {
		this.subMenuItem = subMenuItem;
	}
}
