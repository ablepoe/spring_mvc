package test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.service.AServiceImpl;
import com.spring.service.BServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AServiceImplTest {

	@Autowired
	private AServiceImpl a;
	
	@Autowired
	private BServiceImpl b;
	
	private String _msg;
	private int _type;

	@Before
	public void init(){
		_msg = "msg";
		_type = 1;
	}
	
	@Test
//	@Ignore
	public void testBarA() {
		a.barA();
	}

	@Test
	@Ignore
	public void testFooA() {
		a.fooA(_msg);
	}

	@Test
	@Ignore
	public void testBarB(){
		b.barB(_msg, _type);
	}

	@Test
//	@Ignore
	public void testFooB(){
		b.fooB();
	}
}
