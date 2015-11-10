package test;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo1.service.TransferService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TransferServiceTest {

	@Resource(name = "TransferService")
	private TransferService transferServiceImpl;
	
	@Test
	public void test() {
		try {
			transferServiceImpl.transfer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
