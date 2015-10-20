package com.dhc.service;

import java.util.List;

import com.dhc.entity.IkedaReportBParam;
import com.dhc.entity.IkedaReportBReturn;

/**
 * 
 * @author hanliang 20151020
 *
 */
public interface IkedaReportBService {

	/**
	 * 
	 * @param ikedaReportBParam
	 * @return
	 */
	public List<IkedaReportBReturn> getIkedaReportB(IkedaReportBParam ikedaReportBParam);
	
}
