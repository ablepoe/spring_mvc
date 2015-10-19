package com.dhc.service;

import java.util.List;

import com.dhc.entity.DemoParam;
import com.dhc.entity.DemoReturn;

public interface DemoService {

	public List<DemoReturn> getDemo(DemoParam demoParam);
	public int getDemoSize(DemoParam demoParam);
	public List<DemoReturn> getDemoList(DemoParam demoParam);
	
}
