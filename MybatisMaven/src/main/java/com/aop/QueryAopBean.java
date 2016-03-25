package com.aop;

/**
 * 
 * @author hanliang
 * 自定义POJO类，在aop切面中自定义需要使用的方法
 */
public class QueryAopBean {

	public void beforeDo(){
		System.out.println("before do");
	}
	
}
