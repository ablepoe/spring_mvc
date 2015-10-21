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
import com.dhc.entity.IkedaReportBReturn;
import com.dhc.entity.IkedaReportCParam;
import com.dhc.entity.IkedaReportCReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.IkedaReportCService;

/**
 * 
 * @author hanliang 20151021
 *
 */
@Controller
public class IkedaReportCController {
	
	private Logger log = Logger.getLogger(IkedaReportCController.class);

	@Resource(name = "IkedaReportCService")
	private IkedaReportCService ikedaReportCService;
	
	@ResponseBody
	@RequestMapping(value = "/getIkedaReportCData")
	public ResponseDTO getIkedaReportCData(@RequestBody IkedaReportCParam ikedaReportCParam){
		ResponseDTO resp = new ResponseDTO();
		try{
			List<IkedaReportCReturn> result = ikedaReportCService.getIkedaReportCData(ikedaReportCParam);
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(result);
		} catch(Exception e){
			log.error("Error occured in getIkedaReportCData", e);
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E8001");
    		resp.setMsgContent(CommonUtil.getMessage("E8001"));
		}
		return resp;
	}
}
