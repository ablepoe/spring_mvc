package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.AuthRight;
import com.entity.UserAuth;

@Controller
public class DetailAuthController {
	//ajax 
	@ResponseBody
	@RequestMapping(value="/Main")
	public AuthRight getDetailAuth(@RequestBody UserAuth userAuth,HttpServletRequest req){
		//return obj
		AuthRight ar = new AuthRight();
		//get userAuth from session
		Map<String, UserAuth> userMap = (Map<String, UserAuth>) req.getSession().getAttribute("userAuth");
		//here "1" is func_id,just for e.g.
		if(userMap.containsKey("1") ){
			UserAuth user = userMap.get("1");
			//get user Auth
			if("W".equals(user.getFuncAuth()) ){
				ar.setRight(true);
			}else{
				ar.setRight(false);
			}
		}else{
			ar.setRight(false);	
		}
		return ar;
	}
}
