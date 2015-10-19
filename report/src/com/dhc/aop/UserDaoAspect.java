package com.dhc.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("UserAspect")
@Aspect
public class UserDaoAspect {
	
	private static Logger log =  Logger.getLogger(UserDaoAspect.class);
	//private static Logger log =  Logger.getLogger(com.dhc.dao.impl.UserDaoImpl.class);

	@Pointcut(value="execution(* com.dhc.dao.impl.*.*(..))")
	public void pointCut(){
		
	}
	
	@Before(value = "pointCut()")
	public void before(JoinPoint jp){
//		System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
		log.debug("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}
	
	@After(value = "pointCut()")
	public void after(JoinPoint jp){
//		System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
		log.debug("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
	}
	
	@AfterThrowing(pointcut = "pointCut()",throwing = "ex")
	public void throwEx(JoinPoint jp, Throwable ex){
//		System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
//		System.out.println(ex.getMessage());
		log.error("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		log.error(ex.getMessage());
	}
	
	@Around(value = "pointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
//		System.out.println("process time: " + time + " ms");
		log.debug("process time: " + time + " ms");
		return retVal;
	}
}
