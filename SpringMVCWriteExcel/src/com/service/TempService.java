package com.service;

import java.util.List;

import com.entity.TempParam;
import com.entity.TempReturn;

public interface TempService {

	public List<TempReturn> getTempList(TempParam tempParam);
	
}
