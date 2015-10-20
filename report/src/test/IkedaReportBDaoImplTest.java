package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.IkedaReportBDao;
import com.dhc.entity.IkedaReportBParam;
import com.dhc.entity.IkedaReportBReturn;
import com.dhc.service.IkedaReportBService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class IkedaReportBDaoImplTest {

	@Resource(name = "IkedaReportBDao")
	private IkedaReportBDao ikedaReportBDaoImpl;
	
	@Resource(name = "IkedaReportBService")
	private IkedaReportBService ikedaReportBServiceImpl;
	
	private IkedaReportBParam ikedaReportBParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp() throws Exception {
		ikedaReportBParam = new IkedaReportBParam();
		ikedaReportBParam.setStartDate(20150930);
		ikedaReportBParam.setEndDate(20151013);
	}

	@Test
	@Ignore
	public void testGetIkedaReportB() {
		List<IkedaReportBReturn> ikedaReportBReturn = ikedaReportBDaoImpl.getIkedaReportB(ikedaReportBParam);
		for (int i = 0; i < ikedaReportBReturn.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(ikedaReportBReturn.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
//	@Ignore
	public void testGetIkedaReportBService() {
		List<IkedaReportBReturn> ikedaReportBReturn = ikedaReportBServiceImpl.getIkedaReportB(ikedaReportBParam);
		for (int i = 0; i < ikedaReportBReturn.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(ikedaReportBReturn.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
}
