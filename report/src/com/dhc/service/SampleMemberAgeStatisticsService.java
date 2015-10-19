package com.dhc.service;

import java.util.List;

import com.dhc.entity.SampleMemberAgeStatisticsParam;
import com.dhc.entity.SampleMemberAgeStatisticsReturn;

/**
 * 
 * @author hanliang 20150814
 *
 */
public interface SampleMemberAgeStatisticsService {

	public List<SampleMemberAgeStatisticsReturn> getSampleMemberAgeStatisticsByDate(SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam);
	
}
