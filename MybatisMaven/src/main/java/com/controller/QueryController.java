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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.common.CommonParam;
import com.entity.Command;
import com.entity.Message;
import com.entity.Pagination;
import com.entity.SearchParam;
import com.service.QueryService;
import com.service.impl.QueryServiceImpl;

/**
 * 
 * @author hanliang
 * 服务控制窗口
 */
@Controller
public class QueryController {
	
	@Resource(name="QueryService")
	private QueryService queryService;

	/**
	 * without pagination
	 * @return
	 */
/*	@RequestMapping(value = "/")
	public ModelAndView login(){
		System.out.println("welcome");
		int messageTotalCounts = queryService.getMessageTotalCounts();
		List<Message> messageList = queryService.getAllMessages();
		Pagination pagination = new Pagination();
		pagination.setTotalCounts(messageList.size());
		System.out.println(JSON.toJSONString(pagination));
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageList",messageList);
		mav.addObject("messageTotalCounts", messageTotalCounts);
		mav.addObject("pagination", pagination);
		mav.setViewName("query");
		return mav;
	}*/
	
	@RequestMapping(value = "/")
	public ModelAndView login(){
		System.out.println("welcome");
		Map<String,Object> map = new HashMap<String,Object>();
		map = queryService.getLimitCommands(CommonParam.DEFAULT_PGAENUM);
		List<Message> allMessageList = queryService.getAllMessages();
		ModelAndView mav = new ModelAndView();
		mav.addObject("allMessageList",allMessageList);
		mav.addObject("messageList",map.get("messageList"));
		mav.addObject("pagination", map.get("pagination"));
		mav.setViewName("query");
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/search")
	public Map<String,Object> search(HttpServletRequest request,HttpServletResponse response,@RequestBody Command command){
		System.out.println("search in");
		System.out.println(JSON.toJSONString(command));
		List<Message> messageList = queryService.getSpecialMessages(command);
		Pagination pagination = new Pagination();
		pagination.setTotalCounts(queryService.getSpecialMessageCounts(command));
		System.out.println(JSON.toJSONString(pagination));
		List<Message> allMessageList = queryService.getAllMessages();
		System.out.println(JSON.toJSONString(allMessageList));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allMessageList",allMessageList);
		map.put("messageList", messageList);
		map.put("pagination", pagination);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/batchAdd")
	public Map<String,Object> batchAdd(HttpServletRequest request,HttpServletResponse response,@RequestBody List<Command> commands){
		System.out.println("batchAdd in");
		System.out.println(JSON.toJSONString(commands));
		Boolean status = queryService.insertCommands(commands);
		List<Message> messageList = queryService.getAllMessages();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("messageList", messageList);
		return map;
	}
	
	@RequestMapping(value="/ajaxJump")
	public ModelAndView ajaxJump(){
		SearchParam searchParam = new SearchParam();
		Map<String,Object> map = queryService.getSpecialMessagesByPage(searchParam);
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageList",map.get("messageList"));
		mav.addObject("pagination", map.get("pagination"));
		mav.setViewName("query");
//		List<Message> messageList = queryService.getAllMessages();
//		Pagination pagination = new Pagination();
//		pagination.setTotalCounts(messageList.size());
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("messageList",messageList);
//		mav.addObject("pagination", pagination);
//		mav.setViewName("query");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeOneCommand")
	public Map<String,Object> removeOneCommand(HttpServletRequest request,HttpServletResponse respsonse,@RequestParam(value="id") String id){
		boolean status = queryService.removeOneCommand(Integer.parseInt(id));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeSelected")
	public Map<String,Object> removeSelected(HttpServletRequest request,HttpServletResponse respsonse, @RequestBody List<String> ids){
		boolean status = queryService.removeSelected(ids);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateOneCommand")
	public Map<String,Object> updateOneCommand(HttpServletRequest request,HttpServletResponse respsonse, @RequestBody List<Command> commands){
		boolean status = queryService.updateOneCommand(commands);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/jumpPage")
	public Map<String,Object> jumpPage(HttpServletRequest request,HttpServletResponse response,@RequestBody SearchParam searchParam){
		System.out.println(searchParam.getPageNum());
		Map<String,Object> map = queryService.getSpecialMessagesByPage(searchParam);
		map.put("messageList", map.get("messageList"));
		map.put("pagination", map.get("pagination"));
		return map;
	}
}
