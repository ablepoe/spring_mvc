package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hanliang
 * 使用注解方式的自定义切面实现方法
 */
@Component
@Aspect
public class QueryAopAspectj {
	
	public QueryAopAspectj(){
		
	}

	//定义pointcut，aop需要切入的位置
	@Pointcut(value="execution(* com.service.*.*(..))")
	public void pointcut(){
		
	}
	
	@Before(value = "pointcut()")
	public void before(JoinPoint jp){
		System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}
	
	@After(value = "pointCut()")
	public void after(JoinPoint jp){
		System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}
	
}
