package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class TestAspectAnnotation {

	@Pointcut("execution(* com.spring.service.AServiceImpl.*(..))")
	public void pointCut(){
		
	}
	
	/*@After(value="pointCut()")
	public void doAfter(JoinPoint jp) {
		System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}

	@Around(value="pointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		System.out.println("process time: " + time + " ms");
		return retVal;
	}

	@Before(value="pointCut()")
	public void doBefore(JoinPoint jp) {
		System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}
	
	@AfterThrowing(pointcut="pointCut()",throwing="ex")
	public void doThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		System.out.println(ex.getMessage());
	}*/
}