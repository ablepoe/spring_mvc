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
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.IkedaReportAService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author hanliang 20151010
 *
 */
@Controller
public class IkedaReportAController {

	private Logger log = Logger.getLogger(IkedaReportAController.class);
	
	@Resource(name = "IkedaReportAService")
	private IkedaReportAService ikedaReportAService;
	
	@ResponseBody
	@RequestMapping(value = "/getIkedaReportAData")
	public ResponseDTO getIkedaReportAData(@RequestBody IkedaReportAParam ikedaReportAParam){
		ResponseDTO resp = new ResponseDTO();
		try{
			List<IkedaReportAReturn> result = ikedaReportAService.getIkedaReportA(ikedaReportAParam);
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(result);
		} catch(Exception e){
			log.error("Error occured in getIkedaReportAData", e);
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E6001");
    		resp.setMsgContent(CommonUtil.getMessage("E6001"));
		}
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getIkedaReportAExport")
	public ResponseDTO getIkedaReportADataExport(@RequestBody IkedaReportAParam ikedaReportAParam){
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(ikedaReportAParam));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		ResponseDTO resp = new ResponseDTO();
		CommonUtil commonUtil;
		try{
			commonUtil = new CommonUtil();
			resp = commonUtil.write2excel(Common.IKEDAREPORTA_TEMPLATE_NAME,ikedaReportAService,ikedaReportAParam,null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			commonUtil = null;
		}
		
		resp.setStatus(Common.STATUS_FAILURE);
		return resp;
	}
}
