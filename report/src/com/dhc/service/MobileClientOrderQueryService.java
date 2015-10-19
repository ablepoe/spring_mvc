package com.dhc.service;

import java.util.List;

import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.entity.MobileClientOrderQueryReturn;

/**
 * 
 * @author hanliang 20150826
 *
 */
public interface MobileClientOrderQueryService {

	/**
	 * get moblieClientOrderQuery data
	 * @param moblieClientOrderQueryParam
	 * @return
	 */
	public List<MobileClientOrderQueryReturn> mobileClientOrderQuery(MobileClientOrderQueryParam mobileClientOrderQueryParam);
	
}
