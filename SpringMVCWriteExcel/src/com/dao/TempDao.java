package com.dao;

import java.util.List;

import com.entity.TempParam;
import com.entity.TempReturn;

public interface TempDao {

	public List<TempReturn> getTempList(TempParam tempParam);
	
}
