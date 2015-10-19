package com.dhc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dhc.dao.MobileClientOrderQueryDao;
import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.entity.MobileClientOrderQueryReturn;
import com.dhc.service.MobileClientOrderQueryService;

/**
 * 
 * @author hanliang 20150826
 *
 */
@Service("MobileClientOrderQueryService")
public class MobileClientOrderQueryServiceImpl implements MobileClientOrderQueryService{
	
	@Resource(name="MobileClientOrderQueryDao")
	private MobileClientOrderQueryDao mobileClientOrderQueryDao;

	@Override
	public List<MobileClientOrderQueryReturn> mobileClientOrderQuery(
			MobileClientOrderQueryParam moblieClientOrderQueryParam) {
		return mobileClientOrderQueryDao.mobileClientOrderQuery(moblieClientOrderQueryParam);
	}

	
}
