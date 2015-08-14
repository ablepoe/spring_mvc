package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.TempDao;
import com.entity.TempParam;
import com.entity.TempReturn;
import com.service.TempService;
@Service("TempService")
public class TempServiceImpl implements TempService{

	
	@Autowired
	private TempDao tempDao;
	
	@Override
	public List<TempReturn> getTempList(TempParam tempParam) {
		return tempDao.getTempList(tempParam);
	}

}
