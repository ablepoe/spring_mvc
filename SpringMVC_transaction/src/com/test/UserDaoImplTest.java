package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.MainService;
import com.service.TeacherService;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
public class UserDaoImplTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private MainService mainService;
	
	@Test
	public void testTransaction(){
		mainService.testTransaction();
	}
}
