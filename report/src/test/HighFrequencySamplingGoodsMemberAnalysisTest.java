package test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.HighFrequencySamplingGoodsMemberAnalysisDao;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisParam;
import com.dhc.entity.HighFrequencySamplingGoodsMemberAnalysisReturn;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HighFrequencySamplingGoodsMemberAnalysisTest {

	@Resource(name = "HighFrequencySamplingGoodsMemberAnalysisDao")
	private HighFrequencySamplingGoodsMemberAnalysisDao highFrequencySamplingGoodsMemberAnalysisDao;
	
	private HighFrequencySamplingGoodsMemberAnalysisParam highFrequencySamplingGoodsMemberAnalysisParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	@Before
	public void setUp(){
		highFrequencySamplingGoodsMemberAnalysisParam = new HighFrequencySamplingGoodsMemberAnalysisParam();
		highFrequencySamplingGoodsMemberAnalysisParam.setStartDate(20150101);
		highFrequencySamplingGoodsMemberAnalysisParam.setEndDate(20150301);
		highFrequencySamplingGoodsMemberAnalysisParam.setGoods_code(3002);
		highFrequencySamplingGoodsMemberAnalysisParam.setRepurchase_count(3);
	}
	
	@Test
	public void test() {
		HighFrequencySamplingGoodsMemberAnalysisReturn result = highFrequencySamplingGoodsMemberAnalysisDao.getResults(highFrequencySamplingGoodsMemberAnalysisParam);
		for (int i = 0; i < result.getResults().size(); i++) {
			System.out.println(result.getResults().get(i));
		}
	}

}
