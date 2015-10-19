package com.dhc.dao;

import java.util.List;

import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;

/**
 * 
 * @author hanliang
 * -crm interface
 */
public interface CrmDao {

	public List<CrmReturn> getCrmData(CrmParam crmParam);

	public List<CrmReturn> getCrmDownloadData(CrmParam crmParam);
}
