package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.CrmDao;
import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;
import com.dhc.service.CrmService;

@Service("CrmService")
public class CrmServiceImpl implements CrmService{

	@Resource(name="CrmDao")
	private CrmDao crmDao;
	
	@Override
	public List<CrmReturn> getCrmData(CrmParam crmParam) {
		return crmDao.getCrmData(crmParam);
	}

	@Override
	public List<CrmReturn> getCrmDownloadData(CrmParam crmParam) {
		return crmDao.getCrmDownloadData(crmParam);
	}
}
