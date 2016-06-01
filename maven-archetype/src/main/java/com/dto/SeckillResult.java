package com.dto;

/**
 * 
 * @author hanliang
 * seckill Controller中用到的返回数据对象
 */
public class SeckillResult<T> {

	private boolean status;

	private T data;
	
	private String message;
	
	/**
	 * 请求失败
	 * @param status
	 * @param message
	 */
	public SeckillResult(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	/**
	 * 请求成功
	 * @param status
	 * @param data
	 */
	public SeckillResult(boolean status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
