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
import com.dhc.entity.ResponseDTO;
import com.dhc.entity.ShoppingAnalyzeParam;
import com.dhc.entity.ShoppingAnalyzeReturn;
import com.dhc.service.ShoppingAnalyzeService;

/**
 * 
 * @author hanliang 20150811
 *
 */
@Controller
public class ShoppingAnalyzeController {
	
	private Logger log = Logger.getLogger(ShoppingAnalyzeController.class);

	@Resource(name="ShoppingAnalyzeService")
	private ShoppingAnalyzeService shoppingAnalyzeService;
	
	@ResponseBody
	@RequestMapping("/getFirstShoppingAnalyze")
	public ResponseDTO getFirstShoppingAnalyze(@RequestBody ShoppingAnalyzeParam shoppingAnalyzeParam){
		try{
			List<ShoppingAnalyzeReturn> data = shoppingAnalyzeService.firstShoppingAnalyze(shoppingAnalyzeParam);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(data);
			return resp;
		}catch(Exception e){
			log.error("Error occured in getFirstShoppingAnalyze", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E4001");
    		resp.setMsgContent(CommonUtil.getMessage("E4001"));
			return resp;
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping("/getSecondShoppingAnalyze")
	public ResponseDTO getSecondShoppingAnalyze(@RequestBody ShoppingAnalyzeParam shoppingAnalyzeParam){
		try{
			shoppingAnalyzeService.truncateTempTable();
			shoppingAnalyzeService.createTempTable(shoppingAnalyzeParam);
			List<ShoppingAnalyzeReturn> data = shoppingAnalyzeService.secondShoppingAnalyze();
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(data);
			return resp;
		}catch(Exception e){
			log.error("Error occured in getSecondShoppingAnalyze", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E4002");
    		resp.setMsgContent(CommonUtil.getMessage("E4002"));
			return resp;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getFirstShoppingAnalyzeExport")
	public ResponseDTO getFirstShoppingAnalyzeExport(@RequestBody ShoppingAnalyzeParam shoppingAnalyzeParam,HttpServletRequest req) {

		ResponseDTO resp = new ResponseDTO();
		CommonUtil commonUtil;
		try{
			commonUtil = new CommonUtil();
			resp = commonUtil.write2excel(Common.FIRSTSHOPPINGANALYZE_TEMPLATE_NAME,shoppingAnalyzeService,shoppingAnalyzeParam,1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			commonUtil = null;
		}
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSecondShoppingAnalyzeExport")
	public ResponseDTO getSecondShoppingAnalyzeExport(@RequestBody ShoppingAnalyzeParam shoppingAnalyzeParam,HttpServletRequest req) {

		ResponseDTO resp = new ResponseDTO();
		CommonUtil commonUtil;
		try{
			commonUtil = new CommonUtil();
			resp = commonUtil.write2excel(Common.SECONDSHOPPINGANALYZE_TEMPLATE_NAME,shoppingAnalyzeService,shoppingAnalyzeParam,2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}finally{
			commonUtil = null;
		}
		return resp;
	}
	
}
