package com.dhc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhc.common.Common;
import com.dhc.common.CommonUtil;
import com.dhc.entity.IkedaReportBParam;
import com.dhc.entity.IkedaReportBReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.IkedaReportBService;

/**
 * 
 * @author hanliang 20151020
 *
 */
@Controller
public class IkedaReportBController {

	private Logger log = Logger.getLogger(IkedaReportBController.class);
	
	@Resource(name = "IkedaReportBService")
	private IkedaReportBService ikedaReportBService;
	
	@ResponseBody
	@RequestMapping(value = "/getIkedaReportBData")
	public ResponseDTO getIkedaReportBData(@RequestBody IkedaReportBParam ikedaReportBParam){
		ResponseDTO resp = new ResponseDTO();
		try{
			List<IkedaReportBReturn> result = ikedaReportBService.getIkedaReportB(ikedaReportBParam);
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(result);
		} catch(Exception e){
			log.error("Error occured in getIkedaReportBData", e);
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E7001");
    		resp.setMsgContent(CommonUtil.getMessage("E7001"));
		}
		return resp;
	}
	
	
}
