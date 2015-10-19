package com.dhc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhc.common.Common;
import com.dhc.common.CommonUtil;
import com.dhc.entity.DemoParam;
import com.dhc.entity.DemoReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.DemoService;

@Controller
public class DemoController {

	private Logger log = Logger.getLogger(DemoController.class);
	
	@Resource(name="DemoService")
	private DemoService demoService;
	
	@ResponseBody
	@RequestMapping(value = "/demoSample")
	private ResponseDTO demoample(@RequestBody DemoParam demoParam){
		
		long ts1 = System.currentTimeMillis();
		List<DemoReturn> li = demoService.getDemo(demoParam);
		long ts2 = System.currentTimeMillis();
		System.out.println("getDemo "+ (ts2-ts1));
		int total = demoService.getDemoSize(demoParam);
		long ts3 = System.currentTimeMillis();
		System.out.println("getDemoSize "+ (ts3-ts2));
		
		ResponseDTO resp = new ResponseDTO();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("pageNumber", demoParam.getPageInfo().getPageNumber());
		map.put("pageSize", demoParam.getPageInfo().getPageSize());
		map.put("data", li);
		resp.setData(map);
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/demoExport")
	public ResponseDTO demoExport(@RequestBody DemoParam demoParam,HttpServletRequest req) {

		ResponseDTO resp = new ResponseDTO();
		CommonUtil commonUtil;
		try{
			commonUtil = new CommonUtil();
			resp = commonUtil.write2excel(Common.DEMO_TEMPLATE_NAME,demoService,demoParam,null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			commonUtil = null;
		}
		return resp;
	}
}
