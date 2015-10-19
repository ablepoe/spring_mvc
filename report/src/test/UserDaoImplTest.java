package test;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhc.dao.UserDao;
import com.dhc.entity.HibernateUserFunctionAuth;
import com.dhc.entity.HibernateUserPrimaryKey;

/**
 * 
 * @author hanliang 20160617
 * -junit test for hibernate saveOrUpdate
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
public class UserDaoImplTest {

	@Resource(name="UserDao")
	private UserDao userDaoImpl;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testSaveOrUpdate() {
		List<HibernateUserFunctionAuth> hibernateUsers = new ArrayList<HibernateUserFunctionAuth>();
		HibernateUserFunctionAuth user = new HibernateUserFunctionAuth();
		HibernateUserPrimaryKey primaryKey = new HibernateUserPrimaryKey();
		primaryKey.setUser_Id(3);
		primaryKey.setFunc_Id("6");
		user.setPrimaryKey(primaryKey);
		user.setAuthority("R");
		hibernateUsers.add(user);
//		SessionFactory session = new AnnotationConfiguration().configure().buildSessionFactory() ;
//		session.getCurrentSession().saveOrUpdate(user);
		userDaoImpl.saveOrUpdate(hibernateUsers);
	}

}
