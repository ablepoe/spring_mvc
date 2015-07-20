package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.UserService;

@Controller
public class UserController {

	@Resource(name="UserService")
	private UserService userService;
	
	@RequestMapping(value = "/findUser")
	public ModelAndView findUser(){
		System.out.println("findUser");
		ModelAndView mav = new ModelAndView();
		userService.selectUsersByName("summer");
		mav.setViewName("user");
		return mav;
	}
	
}
