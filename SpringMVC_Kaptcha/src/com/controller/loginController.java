package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.ResponseDTO;

/**
 * 
 * @author hanliang 20160629
 * -validate code controller
 */
@Controller
public class loginController {

	@ResponseBody
	@RequestMapping("/login")
	public ResponseDTO login(@RequestParam("input_code") String input_code, HttpServletRequest request, HttpServletResponse response){
		//get code in image
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//check if input_code equals image code
		boolean flag = false;
		flag = input_code.equals(code);
		//return obj
		ResponseDTO resp = new ResponseDTO();
		resp.setStatus(flag);
		return resp;
	}
}
