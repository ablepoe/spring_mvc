package com.dhc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhc.entity.User;

/**
 * 
 * @author hanliang 20150617
 * -deal common method or direct url access
 *
 */
@Controller
public class CommonController {

	@ResponseBody
	@RequestMapping(value = "/pageInit")
	public User pageInit(){
		//just for the page user login check
		return new User();
	}
	
}
