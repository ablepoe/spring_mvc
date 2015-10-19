package test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.MobileClientOrderQueryDao;
import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.service.MobileClientOrderQueryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MoblieClientOrderQueryTest {

	@Resource(name = "MobileClientOrderQueryDao")
	private MobileClientOrderQueryDao mobileClientOrderQueryDao;
	
	@Resource(name = "MobileClientOrderQueryService")
	private MobileClientOrderQueryService mobileClientOrderQueryService;
	
	private MobileClientOrderQueryParam mobileClientOrderQueryParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp(){
		mobileClientOrderQueryParam = new MobileClientOrderQueryParam();
		mobileClientOrderQueryParam.setStartDate(20150815);
		mobileClientOrderQueryParam.setEndDate(20150820);
	}
	
	@Test
	public void test() {
		mobileClientOrderQueryDao.mobileClientOrderQuery(mobileClientOrderQueryParam);
	}

}
