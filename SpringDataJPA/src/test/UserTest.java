package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.MyUser;
import com.repository.MyUserRepository;
import com.service.MyUserService;

import cn.luxh.app.domain.User;
import cn.luxh.app.repository.UserRepository;
import cn.luxh.app.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {
    
    
    //@Autowired
    private UserService userService;
    
    @Autowired
    private MyUserService myUserService;
    
    //根据id查找用户
    //@Test
    public void testFindUserById() {
        Integer id = 1;
        User user = userService.findUserById(id);
        if(user != null){
        	System.out.println(user.getValue());
            System.out.println(user.getName());
            System.out.println(user.getPassword());
        }
        Assert.assertEquals(null,user);
    }
    
    @Test
    public void testFindByUsernameAndPassword(){
    	MyUser myUser = myUserService.findByNameAndPassword("hmk", "pwd");
    	if(myUser != null){
    		System.out.println(myUser.getId());
            System.out.println(myUser.getValue());
            System.out.println(myUser.getName());
            System.out.println(myUser.getPassword());
    	}
    	Assert.assertEquals(null,myUser);
    }
}