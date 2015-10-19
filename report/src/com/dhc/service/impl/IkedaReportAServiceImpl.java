package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.IkedaReportADao;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;
import com.dhc.service.IkedaReportAService;

/**
 * 
 * @author hanliang 20151010
 *
 */
@Service("IkedaReportAService")
public class IkedaReportAServiceImpl implements IkedaReportAService {

	@Resource(name = "IkedaReportADao")
	private IkedaReportADao ikedaReportADao;
	
	@Override
	public List<IkedaReportAReturn> getIkedaReportA(
			IkedaReportAParam ikedaReportAParam) {
		List<IkedaReportAReturn> result = ikedaReportADao.getIkedaReportA(ikedaReportAParam);
		return result;
	}

	@Override
	public List<IkedaReportAReturn> getIkedaReportAExport(IkedaReportAParam ikedaReportAParam) {
		List<IkedaReportAReturn> result = ikedaReportADao.getIkedaReportA(ikedaReportAParam);
		//sort
		
		System.out.println(ikedaReportAParam.getSortName()+"|"+ikedaReportAParam.getSortOrder());
		
		return result;
	}

}
