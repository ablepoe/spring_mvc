package com.exception;

/**
 * 
 * @author hanliang
 * 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7781284985161562059L;

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}
	
}
