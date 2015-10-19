package com.dhc.dao;

import java.util.List;

import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;

/**
 * 
 * @author hanliang 20151010
 *
 */
public interface IkedaReportADao {

	/**
	 * 
	 * @param ikedaReportAParam
	 * @return
	 */
	public List<IkedaReportAReturn> getIkedaReportA(IkedaReportAParam ikedaReportAParam);
	
}
