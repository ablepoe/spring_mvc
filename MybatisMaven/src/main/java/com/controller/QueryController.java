package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.entity.Command;
import com.entity.Message;
import com.service.QueryService;

/**
 * 
 * @author hanliang
 * 服务控制窗口
 */
@Controller
public class QueryController {
	
	@Resource(name="QueryService")
	private QueryService queryService;

	@RequestMapping(value = "/")
	public ModelAndView login(){
		System.out.println("welcome");
		int messageTotalCounts = queryService.getMessageTotalCounts();
		List<Message> messageList = queryService.getAllMessages();
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageList",messageList);
		mav.addObject("messageTotalCounts", messageTotalCounts);
		mav.setViewName("query");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/search")
	public Map<String,List<Message>> search(HttpServletRequest request,HttpServletResponse response,@RequestBody Command command){
		System.out.println("search in");
		System.out.println(JSON.toJSONString(command));
		List<Message> messageList = queryService.getSpecialMessages(command);
		Map<String,List<Message>> map = new HashMap<String,List<Message>>();
		map.put("messageList", messageList);
		return map;
	}
	
}
