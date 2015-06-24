package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.enetity.User;
import com.enetity.UserService;
@Controller
public class IndexController {
	
/*    @RequestMapping("/index")
    public String index() {
    	System.out.println("index");
        return "index";
    }*/
    
    @RequestMapping("/index")
    public ModelAndView index() {
    	System.out.println("index");
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("index");
        return mav;
    }
    
    
    
    
    @RequestMapping(value="/test", method=RequestMethod.POST )
    public void test(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("test");
    	String parm = request.getParameter("mytest");
    	String parm2 = request.getParameter("mytest2");
    	System.out.println("parm is "+parm);
    	System.out.println("parm2 is "+parm2);
    	String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
    	ObjectMapper objectMapper = new ObjectMapper();
    	PrintWriter out;
		try {
			out = response.getWriter();
	    	objectMapper.writeValue(out, json);
	    	out.flush();
	    	out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
/*    @RequestMapping(value="/test", method=RequestMethod.POST )
    @ResponseBody
    public User test(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("responsebody");
    	User u = new User();
    	u.setName("user");
    	u.setPassword("password");
    	return u;*/
/*    	ObjectMapper mapper = new ObjectMapper();
    	String obj = null;
		try {
			obj = mapper.writeValueAsString(u);
			System.out.println(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return obj;*/
    }
    
//    @RequestMapping(value="/test" ,method=RequestMethod.GET)
//    public String test2(){
//    	System.out.println("test2");
//    	return "index";
//    }
    
//}