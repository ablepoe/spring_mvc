package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.TempDao;
import com.entity.TempParam;
import com.entity.TempReturn;

@Repository("TempDao")
public class TempDaoImpl implements TempDao{

	@Override
	public List<TempReturn> getTempList(TempParam tempParam) {
		TempReturn tr = new TempReturn();
		tr.setReturn_1("1");
		tr.setReturn_2("2");
		List<TempReturn> li = new ArrayList<TempReturn>();
		for (int i = 0; i < 3; i++) {
			li.add(tr);	
		}
		return li;
	}

}
