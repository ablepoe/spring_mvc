package com.dao;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.SuccessKilled;

/**
 * 
 * @author hanliang
 * SuccessKilledDao的junit测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	@Ignore
	public void testInsertSuccessKilled() {
		long seckill_id = 1000l;
		long user_phone = 13916078970l;
		int i =  successKilledDao.insertSuccessKilled(seckill_id,user_phone);
		System.out.println(i);
	}

	@Test
	@Ignore
	public void testQueryByIdWithSeckill() {
		long seckill_id = 1000l;
		long user_phone = 13916078970l;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckill_id,user_phone);
		System.out.println(successKilled.toString());
	}

}
