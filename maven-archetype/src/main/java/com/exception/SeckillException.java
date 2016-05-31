package com.exception;

/**
 * 
 * @author hanliang
 * 所有seckill相关的异常父类
 */
public class SeckillException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1643056073110950680L;

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String message) {
		super(message);
	}
	
}
