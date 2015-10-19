package com.dhc.dao;

import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisParam;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisReturn;

/**
 * 
 * @author hanliang 20150812
 * 
 */
public interface HighFrequencySamplingGoodsMemberAnalysisDao {

	public HighFrequencySamplingGoodsMemberAnalysisReturn getResults(HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam);
	
}
