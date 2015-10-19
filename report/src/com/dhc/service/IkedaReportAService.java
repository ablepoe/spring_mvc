package com.dhc.service;

import java.util.List;

import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;

/**
 * 
 * @author hanliang 20151010
 *
 */
public interface IkedaReportAService {

	/**
	 * 
	 * @param ikedaReportAParam
	 * @return
	 */
	public List<IkedaReportAReturn> getIkedaReportA(IkedaReportAParam ikedaReportAParam);
	
	/**
	 * 
	 * @param ikedaReportAParam
	 * @return
	 */
	public List<IkedaReportAReturn> getIkedaReportAExport(IkedaReportAParam ikedaReportAParam);
	
}
