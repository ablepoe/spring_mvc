package com.exception;

/**
 * 
 * @author hanliang
 * 重复秒杀抛出异常
 */
public class RepeatKillException extends SeckillException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6543866963187209485L;

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}

}
