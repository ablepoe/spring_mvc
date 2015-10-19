package com.dhc.dao;

import java.util.List;

import com.dhc.entity.DemoParam;
import com.dhc.entity.DemoReturn;


/**
 * 
 * @author hanliang 20150702
 *
 */
public interface DemoDao {

	public List<DemoReturn> getDemo(DemoParam demoParam);
	public int getDemoSize(DemoParam demoParam);
	
	public List<DemoReturn> getDemoList(DemoParam demoParam);
}
