package com.dhc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author hanliang
 * -handle exception
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	
	private Logger log = Logger.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		log.error(e.getMessage(),e);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Index");
		return mav;
	}
}
