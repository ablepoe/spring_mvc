package com.entity;

import java.io.Serializable;

public class ResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4188853432780797141L;

	
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
