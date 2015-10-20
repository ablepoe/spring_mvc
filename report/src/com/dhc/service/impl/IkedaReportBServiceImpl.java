package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.IkedaReportBDao;
import com.dhc.entity.IkedaReportBParam;
import com.dhc.entity.IkedaReportBReturn;
import com.dhc.service.IkedaReportBService;

/**
 * 
 * @author hanliang 20151022
 *
 */
@Service("IkedaReportBService")
public class IkedaReportBServiceImpl implements IkedaReportBService{

	@Resource(name = "IkedaReportBDao")
	private IkedaReportBDao IkedaReportBDao;
	
	@Override
	public List<IkedaReportBReturn> getIkedaReportB(
			IkedaReportBParam ikedaReportBParam) {
		List<IkedaReportBReturn> result = IkedaReportBDao.getIkedaReportB(ikedaReportBParam);
		return result;
	}

	
	
}
