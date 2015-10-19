package com.dhc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhc.common.Common;
import com.dhc.common.CommonUtil;
import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.CrmService;

/**
 * 
 * @author hanliang 20150626
 * -crmData
 */
@Controller
public class CrmController {
	
	private Logger log = Logger.getLogger(CrmController.class);

	@Resource(name = "CrmService")
	private CrmService crmService;
	
	
	@ResponseBody
	@RequestMapping(value = "/crmSample")
	private ResponseDTO crmSample(@RequestBody CrmParam crmParam){
		
		long ts1 = System.currentTimeMillis();
		List<CrmReturn> li = crmService.getCrmData(crmParam);
		long ts2 = System.currentTimeMillis();
		System.out.println(ts2-ts1);
		
		ResponseDTO resp = new ResponseDTO();
		resp.setData(li);
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/crmExport")
	public ResponseDTO crmExport(@RequestBody CrmParam crmParam,HttpServletRequest req) {
		
		ResponseDTO resp = new ResponseDTO();
		CommonUtil commonUtil;
		try{
			commonUtil = new CommonUtil();
			resp = commonUtil.write2excel(Common.DEMO_TEMPLATE_NAME,crmService,crmParam,null);	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			commonUtil = null;
		}
		return resp;
		
	}
}
