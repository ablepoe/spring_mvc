package com.dhc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.HighFrequencySamplingGoodsMemberAnalysisDao;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisParam;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisReturn;
import com.dhc.service.HighFrequencySamplingGoodsMemberAnalysisService;

/**
 * 
 * @author hanliang 20150812
 *
 */
@Service("HighFrequencySamplingGoodsMemberAnalysisService")
public class HighFrequencySamplingGoodsMemberAnalysisServiceImpl implements HighFrequencySamplingGoodsMemberAnalysisService{

	@Resource(name = "HighFrequencySamplingGoodsMemberAnalysisDao")
	private HighFrequencySamplingGoodsMemberAnalysisDao highFrequencySamplingGoodsMemberAnalysisDao;
	
	@Override
	public HighFrequencySamplingGoodsMemberAnalysisReturn getResults(
			HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam) {
		return highFrequencySamplingGoodsMemberAnalysisDao.getResults(highFrequencySamplingGoodsMemberAnalysisParam);
	}

}
