package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author hanliang 2015/05/27
 * -exception occured in method,use its' own exception handler
 *
 */
@Controller
public class SomeOtherController {

	@RequestMapping(value = "/SomeOtherControllerTest")
	public ModelAndView javaException(){
		System.out.println("SomeOtherControllerTest");
		Object obj = null;
		//make the exception occur
		obj.toString();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("SomeOtherController");
		return mav;
	}
	
	 /** 
     * deal with the exception inside this class
     * @return 
     */  
    @ExceptionHandler({java.lang.Exception.class})  
    public ModelAndView exception(java.lang.Exception e) {  
    	System.out.println("java.lang.Exception occured in SomeOtherController");
        ModelAndView mav = new ModelAndView();
		mav.setViewName("SomeOtherController");
		return mav;
    } 
}
