package com.dhc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhc.common.Common;
import com.dhc.common.CommonUtil;
import com.dhc.entity.ResponseDTO;
import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthReturn;
import com.dhc.entity.ShopMappedCity;
import com.dhc.service.SampleBuyBackAnalysisByMonthService;

/**
 * 
 * @author hanliang 20150804
 *
 */
@Controller
public class SampleBuyBackAnalysisByMonthController {

	private Logger log = Logger.getLogger(CrmController.class);
	
	@Resource(name="SampleBuyBackAnalysisByMonthService")
	private SampleBuyBackAnalysisByMonthService sampleBuyBackAnalysisByMonthService;
	
	@ResponseBody
	@RequestMapping(value = "/getProvince")
	private ResponseDTO getProvince(){
		try{
			List<String> li = sampleBuyBackAnalysisByMonthService.getProvince();
			ResponseDTO resp = new ResponseDTO();
			resp.setData(li);
			resp.setStatus(Common.STATUS_SUCCESS);
			return resp;
		} catch (Exception e) {
			log.error("Error occured in getProvince", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E3001");
    		resp.setMsgContent(CommonUtil.getMessage("E3001"));
			return resp;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getShopMappedCity")
	private ResponseDTO getShopMappedCity(){
		try{
			List<ShopMappedCity> li = sampleBuyBackAnalysisByMonthService.getShopMappedCity();
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(li);
			return resp;
		} catch (Exception e) {
			log.error("Error occured in getShopMappedCity", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E3002");
    		resp.setMsgContent(CommonUtil.getMessage("E3002"));
			return resp;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSampleBuyBackAnalysisByMonth")
	private ResponseDTO getSampleBuyBackAnalysisByMonth(HttpServletRequest request){
		try{
			String _startMonth = (String) request.getSession().getAttribute("startMonth");
			String _endMonth = (String) request.getSession().getAttribute("endMonth");
			String _pathWay = (String) request.getSession().getAttribute("pathWay");
			String _provincevalue = (String) request.getSession().getAttribute("provinceValue");
			String _city = (String) request.getSession().getAttribute("city");
			
			removeSessionAttribute(request);
			
			SampleBuyBackAnalysisByMonthParam sampleBuyBackAnalysisByMonthParam = new SampleBuyBackAnalysisByMonthParam(); 
			sampleBuyBackAnalysisByMonthParam.setStartMonth(Integer.parseInt(_startMonth));
			sampleBuyBackAnalysisByMonthParam.setEndMonth(Integer.parseInt(_endMonth));
			sampleBuyBackAnalysisByMonthParam.setPathWay(Integer.parseInt(_pathWay));
			sampleBuyBackAnalysisByMonthParam.setProvince(_provincevalue);
			sampleBuyBackAnalysisByMonthParam.setCity(_city);
			
			List<String> provinceList = sampleBuyBackAnalysisByMonthService.getProvince();
			List<SampleBuyBackAnalysisByMonthReturn> li = sampleBuyBackAnalysisByMonthService.getSampleBuyBackAnalysisByMonth(sampleBuyBackAnalysisByMonthParam,provinceList);
			
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(li);
			return resp;
		} catch (Exception e) {
			log.error("Error occured in getSampleBuyBackAnalysisByMonth", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E3003");
    		resp.setMsgContent(CommonUtil.getMessage("E3003"));
			return resp;
		}
	}
	
	@RequestMapping(value = "/getMonthDuration")
	private ModelAndView getMonthDuration(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		try {
			request.setCharacterEncoding("utf-8");
			String startMonth = request.getParameter("startMonth");
			String endMonth = request.getParameter("endMonth");
			String pathWay = request.getParameter("pathWay");
			String provinceValue = request.getParameter("provinceValue");
			String city = request.getParameter("city");
			
			setSessionAttribute(request, startMonth, endMonth, pathWay, provinceValue, city);
			
			List<Integer> li = sampleBuyBackAnalysisByMonthService.getMonthDuration(startMonth,endMonth);
			mav.addObject("dataList", li);
			mav.setViewName("function/SampleBuyBackAnalysisByMonthData");
			return mav;
		} catch (Exception e) {
			log.error("Error occured in getMonthDuration", e);
			return null;
		}
	}
	
	private void setSessionAttribute(HttpServletRequest request, String startMonth, String endMonth, String pathWay, String provinceValue, String city){
		request.getSession().setAttribute("startMonth", startMonth);
		request.getSession().setAttribute("endMonth", endMonth);
		request.getSession().setAttribute("pathWay", pathWay);
		request.getSession().setAttribute("provinceValue", provinceValue);
		request.getSession().setAttribute("city", city);
	}
	
	private void removeSessionAttribute(HttpServletRequest request){
		request.getSession().removeAttribute("startMonth");
		request.getSession().removeAttribute("endMonth");
		request.getSession().removeAttribute("pathWay");
		request.getSession().removeAttribute("provinceValue");
		request.getSession().removeAttribute("city");
	}
	
}
