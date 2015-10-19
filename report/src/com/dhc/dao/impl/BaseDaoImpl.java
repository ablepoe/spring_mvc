package com.dhc.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 
 * @author hanliang 20150617
 * -base database common template
 *
 */
public class BaseDaoImpl {

	//
//	@Resource(name="npJdbcDb")
//	protected NamedParameterJdbcTemplate npJdbcDb;
	
	//demo db
//	@Resource(name="demoJdbcDb")
//	protected NamedParameterJdbcTemplate demoJdbcDb;
	
	//main db
	@Resource(name="crmJdbcDb")
	protected NamedParameterJdbcTemplate crmJdbcDb;
}
