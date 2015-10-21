package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.IkedaReportCDao;
import com.dhc.entity.IkedaReportCParam;
import com.dhc.entity.IkedaReportCReturn;
import com.dhc.service.IkedaReportCService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class IkedaReportCDaoImplTest {
	
	@Resource(name = "IkedaReportCService")
	private IkedaReportCService ikedaReportCService;
	
	@Resource(name = "IkedaReportCDao")
	private IkedaReportCDao ikedaReportCDaoImpl;
	
	private IkedaReportCParam ikedaReportCParam;
	
	ObjectMapper mapper = new ObjectMapper();
	@Before
	public void setUp() throws Exception {
		ikedaReportCParam = new IkedaReportCParam();
		ikedaReportCParam.setStartDate(20150930);
		ikedaReportCParam.setEndDate(20151021);
	}

	@Test
	@Ignore
	public void testGetIkedaReportCData() {
		List<IkedaReportCReturn> list = ikedaReportCDaoImpl.getIkedaReportCData(ikedaReportCParam);
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(list.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}	
		}
		
	}

	@Test
//	@Ignore
	public void testGetIkedaReportCDataService() {
		List<IkedaReportCReturn> list = ikedaReportCService.getIkedaReportCData(ikedaReportCParam);
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(list.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
}
