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
import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.entity.MobileClientOrderQueryReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.MobileClientOrderQueryService;

/**
 * 
 * @author hanliang 20150826
 *
 */
@Controller
public class MobileClientOrderQueryController {
	
	private Logger log = Logger.getLogger(MobileClientOrderQueryController.class);

	@Resource(name = "MobileClientOrderQueryService")
	private MobileClientOrderQueryService mobileClientOrderQueryService;
	
	@ResponseBody
	@RequestMapping(value = "/getMobileClientOrder")
	public ResponseDTO getMobileClientOrder(@RequestBody MobileClientOrderQueryParam mobileClientOrderQueryParam){
		try{
			List<MobileClientOrderQueryReturn> data = mobileClientOrderQueryService.mobileClientOrderQuery(mobileClientOrderQueryParam);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(data);
			return resp;
		}catch(Exception e){
			log.error("Error occured in getMobileClientOrder", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E5001");
    		resp.setMsgContent(CommonUtil.getMessage("E5001"));
			return resp;
		}
	}
	 
	
}
