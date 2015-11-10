package test;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo2.service.TransferService;

/**
 * ������ ע�ⷽʽ
 * @author hanliang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext2.xml")
public class TransferServiceTest2 {

	@Resource(name = "TransferService2")
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
