package com.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.Seckill;

/**
 * 
 * @author hanliang
 * SeckillDao的junit测试类
 * 使用RunWith将spring和mybatis整合在一起
 * ContextConfiguration指定spring配置文件的位置
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	@Resource
	private SeckillDao seckillDao;

	@Test
//	@Ignore
	public void testReduceNumber() {
		long id = 1000l;
		Date date = new Date();
		int i = seckillDao.reduceNumber(id, date);
		System.out.println(i);
	}

	@Test
	@Ignore
	public void testQueryById() {
		long id = 1000l;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.toString());
	}

	@Test
	@Ignore
	public void testQueryAll() {
		List<Seckill> li = seckillDao.queryAll(0,100);
		for(Seckill seckill : li){
			System.out.println(seckill.toString());
		}
	}

}
