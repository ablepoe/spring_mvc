package com.dhc.dao;

import java.util.List;

import com.dhc.entity.IkedaReportCParam;
import com.dhc.entity.IkedaReportCReturn;


/**
 * 
 * @author hanliang 20151021
 *
 */
public interface IkedaReportCDao {

	/**
	 * 
	 * @param ikedaReportParam
	 * @return
	 */
	public List<IkedaReportCReturn> getIkedaReportCData(IkedaReportCParam ikedaReportParam);
	
}
