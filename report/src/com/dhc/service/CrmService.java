package com.dhc.service;

import java.util.List;

import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;

public interface CrmService {

	public List<CrmReturn> getCrmData(CrmParam crmParam);
	
	public List<CrmReturn> getCrmDownloadData(CrmParam crmParam);
}
