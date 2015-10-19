package com.dhc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhc.common.Common;
import com.dhc.entity.ResponseDTO;
import com.dhc.entity.SampleMemberAgeStatisticsParam;
import com.dhc.entity.SampleMemberAgeStatisticsReturn;
import com.dhc.service.SampleMemberAgeStatisticsService;

/**
 * 
 * @author hanliang 20150814
 *
 */
@Controller
public class SampleMemberAgeStatisticsController {
	
	private Logger log = Logger.getLogger(SampleMemberAgeStatisticsController.class);
	
	@Resource(name="SampleMemberAgeStatisticsService")
	private SampleMemberAgeStatisticsService sampleMemberAgeStatisticsService;
	
	
	@ResponseBody
	@RequestMapping(value = "/getSampleMemberAgeStatisticsByDateReturn")
	public ResponseDTO getSampleMemberAgeStatisticsByDateReturn(@RequestBody SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam){
		ResponseDTO resp = new ResponseDTO();
		List<SampleMemberAgeStatisticsReturn> li = sampleMemberAgeStatisticsService.getSampleMemberAgeStatisticsByDate(sampleMemberAgeStatisticsParam);
		
		resp.setData(li);
		resp.setStatus(Common.STATUS_SUCCESS);
		return resp;
	}
	
}
