package com.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.impl.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
public class UserDaoImplTest {

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Test
	public void testGetConnection(){
		int i;
		try {
			i = userDaoImpl.getConnection();
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
