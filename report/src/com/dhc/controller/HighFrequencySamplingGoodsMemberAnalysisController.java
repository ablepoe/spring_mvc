package com.dhc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhc.common.CglibBean;
import com.dhc.common.Common;
import com.dhc.common.CommonUtil;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisParam;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisReturn;
import com.dhc.entity.ResponseDTO;
import com.dhc.service.HighFrequencySamplingGoodsMemberAnalysisService;

@Controller
public class HighFrequencySamplingGoodsMemberAnalysisController {

	private Logger log = Logger.getLogger(HighFrequencySamplingGoodsMemberAnalysisController.class);
	
	@Resource(name="HighFrequencySamplingGoodsMemberAnalysisService")
	private HighFrequencySamplingGoodsMemberAnalysisService highFrequencySamplingGoodsMemberAnalysisService;
	
	@RequestMapping(value = "/getHighFrequencyDuration")
	public ModelAndView getHighFrequencyDuration(HttpServletRequest request){
		try{
			ModelAndView mav = new ModelAndView();
			String goods_code = request.getParameter("goods_code");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String repurchase_count = request.getParameter("re_count");

			setSessionAttribute(request, startDate, endDate, goods_code, repurchase_count);
			
			mav.addObject("repurchase_count", Integer.parseInt(repurchase_count));
			mav.setViewName("function/HighFrequencySamplingGoodsMemberAnalysisData");
			return mav;
		}catch(Exception e){
			log.error("Error occured in getHighFrequencyDuration", e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getHighFrequencyDurationData")
	public ResponseDTO getHighFrequencyDurationData(HttpServletRequest request){
		try{
			String _startDate = (String) request.getSession().getAttribute("startDate");
			String _endDate = (String) request.getSession().getAttribute("endDate");
			String _goods_code = (String) request.getSession().getAttribute("goods_code");
			String _repurchase_count = (String) request.getSession().getAttribute("repurchase_count");
			
			removeSessionAttribute(request);
			
			HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam = new HighFrequencySamplingGoodsMemberAnalysisParam();
			highFrequencySamplingGoodsMemberAnalysisParam.setGoods_code(Integer.parseInt(_goods_code));
			highFrequencySamplingGoodsMemberAnalysisParam.setStartDate(Integer.parseInt(_startDate));
			highFrequencySamplingGoodsMemberAnalysisParam.setEndDate(Integer.parseInt(_endDate));
			highFrequencySamplingGoodsMemberAnalysisParam.setRepurchase_count(Integer.parseInt(_repurchase_count));
			//get data
			HighFrequencySamplingGoodsMemberAnalysisReturn  data = highFrequencySamplingGoodsMemberAnalysisService.getResults(highFrequencySamplingGoodsMemberAnalysisParam);
			//set properties
	        HashMap<String, Class<?>> propertyMap = new HashMap<String, Class<?>>();    
			propertyMap.put("goods_code", Class.forName("java.lang.Integer"));
	        for (int i = 0; i < data.getResults().size(); i++) {
				propertyMap.put("_"+(i+2), Class.forName("java.lang.Integer"));
			}
	        propertyMap.put("average", Class.forName("java.lang.Integer"));
	        //generate dynamic bean
	        CglibBean bean = new CglibBean(propertyMap);
	        //goods_code value
	        bean.setValue("goods_code", Integer.parseInt(_goods_code));
	        int sum = 0;
			for (int i = 0; i < data.getResults().size(); i++) {
				bean.setValue("_"+(i+2), data.getResults().get(i));
				sum += data.getResults().get(i);
			}
			//average value int
			bean.setValue("average", sum/data.getResults().size());
			
			//get bean entity    
	        Object object = bean.getObject();    
	        
	        List<Object> resultList = new ArrayList<Object>();
	        resultList.add(object);
	        
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_SUCCESS);
			resp.setData(resultList);
			return resp;
		}catch(Exception e){
			log.error("Error occured in getHighFrequencyDurationData", e);
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E2001");
    		resp.setMsgContent(CommonUtil.getMessage("E2001"));
			return resp;
		}
	}
	
	private void setSessionAttribute(HttpServletRequest request, String startDate, String endDate, String goods_code, String repurchase_count){
		request.getSession().setAttribute("startDate", startDate);
		request.getSession().setAttribute("endDate", endDate);
		request.getSession().setAttribute("goods_code", goods_code);
		request.getSession().setAttribute("repurchase_count", repurchase_count);
	}
	
	private void removeSessionAttribute(HttpServletRequest request){
		request.getSession().removeAttribute("startDate");
		request.getSession().removeAttribute("endDate");
		request.getSession().removeAttribute("goods_code");
		request.getSession().removeAttribute("repurchase_count");
	}
}
