package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.IkedaReportCDao;
import com.dhc.entity.IkedaReportCParam;
import com.dhc.entity.IkedaReportCReturn;
import com.dhc.service.IkedaReportCService;

/**
 * 
 * @author hanliang 20151021
 *
 */
@Service("IkedaReportCService")
public class IkedaReportCServiceImpl implements IkedaReportCService{

	@Resource( name = "IkedaReportCDao")
	private IkedaReportCDao ikedaReportCDaoImpl;

	@Override
	public List<IkedaReportCReturn> getIkedaReportCData(
			IkedaReportCParam ikedaReportParam) {
		List<IkedaReportCReturn> result = ikedaReportCDaoImpl.getIkedaReportCData(ikedaReportParam);
		return result;
	}
	
	
}
