package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.IkedaReportADao;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.IkedaReportAReturn;
import com.dhc.entity.MobileClientOrderQueryParam;
import com.dhc.service.MobileClientOrderQueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class IkedaReportADaoImplTest {

	@Resource(name = "IkedaReportADao")
	private IkedaReportADao ikedaReportADaoImpl;
	
	private IkedaReportAParam ikedaReportAParam;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp() throws Exception {
		ikedaReportAParam = new IkedaReportAParam();
		ikedaReportAParam.setStartDate(20150815);
		ikedaReportAParam.setEndDate(20151013);
	}

	@Test
	@Ignore
	public void testGetIkedaReportA() {
		List<IkedaReportAReturn> ikedaReportAReturn = ikedaReportADaoImpl.getIkedaReportA(ikedaReportAParam);
		for (int i = 0; i < ikedaReportAReturn.size(); i++) {
			try {
				System.out.println(mapper.writeValueAsString(ikedaReportAReturn.get(i)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test(){
//		String start = String.valueOf(20151005);
//		Calendar startCal = Calendar.getInstance();
//		System.out.println(start.substring(0, 4));
//		System.out.println(start.substring(4, 6));
//		System.out.println(start.substring(6));
//		double a = (double)1/(20);
//		System.out.println(a);
//		System.out.println(Math.round(a * 10000 ) / 100.0);
//		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");  
//		df.format(Math.round(a * 10000 ) / 100.0);
//		System.out.println(df.format(a));
		
		
		
		
		
		double d = 1/239.0 ;
		double total_turnrate_d = (double)1/(20.0) ;
		System.out.println(Math.round(total_turnrate_d * 10000 ) / 100.0);
		System.out.println(Math.round(total_turnrate_d * 100.0 ));
		BigDecimal bg = new BigDecimal(Math.round(total_turnrate_d * 10000 ) / 100.0);  
		System.out.println(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());  
		
	}
}
