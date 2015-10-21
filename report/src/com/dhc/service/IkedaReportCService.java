package com.dhc.service;

import java.util.List;

import com.dhc.entity.IkedaReportCParam;
import com.dhc.entity.IkedaReportCReturn;

/**
 * 
 * @author hanliang 20151021
 *
 */
public interface IkedaReportCService {

	/**
	 * 
	 * @param ikedaReportParam
	 * @return
	 */
	public List<IkedaReportCReturn> getIkedaReportCData(IkedaReportCParam ikedaReportParam);
	
}
