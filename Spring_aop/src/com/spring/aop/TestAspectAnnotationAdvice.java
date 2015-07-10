package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspectAnnotationAdvice {

	@After("com.spring.aop.TestAspectAnnotation.pointCut()")
	public void doAfter(JoinPoint jp) {
		System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}

	@Around(value="com.spring.aop.TestAspectAnnotation.pointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		System.out.println("process time: " + time + " ms");
		return retVal;
	}

	@Before(value="com.spring.aop.TestAspectAnnotation.pointCut()")
	public void doBefore(JoinPoint jp) {
		System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}
	
	@AfterThrowing(pointcut="com.spring.aop.TestAspectAnnotation.pointCut()",throwing="ex")
	public void doThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		System.out.println(ex.getMessage());
	}
}
