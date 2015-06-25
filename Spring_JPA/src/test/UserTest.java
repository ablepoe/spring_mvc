package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.UserDao;
import com.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Test 
	public void save(){  
        User user = new User();
        user.setValue("spring");
        userDaoImpl.save(user);
    }
	
	//@Test
	public void update(){
		User user = new User();
		user.setId(3);
        user.setValue("spring");
        userDaoImpl.update(user);
	}
	
	@Test
	public void delete(){
		User user = new User();
		user.setId(4);
        userDaoImpl.delete(user);
	}
}
