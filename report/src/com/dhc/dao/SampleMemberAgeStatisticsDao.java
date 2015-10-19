package com.dhc.dao;

import java.util.List;

import com.dhc.entity.SampleMemberAgeStatisticsParam;
import com.dhc.entity.SampleMemberAgeStatisticsQuery;

/**
 * 
 * @author hanliang 20150814
 *
 */
public interface SampleMemberAgeStatisticsDao {

	public List<SampleMemberAgeStatisticsQuery> getSampleMemberAgeStatisticsByDate(SampleMemberAgeStatisticsParam sampleMemberAgeStatisticsParam);
	
}
