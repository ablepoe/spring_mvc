package com.entity;

import java.io.Serializable;

public class AuthRight implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8896027072087633902L;
	//user auth right
	private boolean right;

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
}
