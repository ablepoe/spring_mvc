package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.DemoDao;
import com.dhc.entity.DemoParam;
import com.dhc.entity.DemoReturn;
import com.dhc.service.DemoService;

@Service("DemoService")
public class DemoServiceImpl implements DemoService{

	@Resource(name="DemoDao")
	private DemoDao demoDao;
	
	@Override
	public List<DemoReturn> getDemo(DemoParam demoParam) {
		return demoDao.getDemo(demoParam);
	}

	@Override
	public List<DemoReturn> getDemoList(DemoParam demoParam) {
		return demoDao.getDemoList(demoParam);
	}

	@Override
	public int getDemoSize(DemoParam demoParam) {
		return demoDao.getDemoSize(demoParam);
	}
	
	

}
