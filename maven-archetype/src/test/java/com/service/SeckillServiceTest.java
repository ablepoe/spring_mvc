package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.entity.Seckill;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;

/**
 * 
 * @author hanliang
 * SeckillService的junit测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private SeckillService seckillService;
	
	@Test
	@Ignore
	public void testGetSeckillList() {
		List<Seckill> li = seckillService.getSeckillList();
		log.info("li={}",li);
	}

	@Test
	@Ignore
	public void testGetSeckillById() {
		long id = 1000l;
		Seckill seckill = seckillService.getSeckillById(id);
		log.info("seckill={}",seckill);
	}

	@Test
	@Ignore
	public void testExportSeckillUrl() {
		long id = 1001l;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		log.info("exposer={}",exposer);
	}

	@Test
	@Ignore
	public void testExecuteSeckill() {
		long id = 1001l;
		long user_phone = 13916012310l;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		log.info("exposer={}",exposer);
		if(exposer.isExposed()){
			try{
				SeckillExecution seckillExecution = seckillService.executeSeckill(id, user_phone, exposer.getMd5());
				log.info("seckillExecution={}",seckillExecution);	
			} catch (SeckillCloseException e1){
				log.error(e1.getMessage());
				throw e1;
			} catch (RepeatKillException e2){
				log.error(e2.getMessage());
				throw e2;
			} catch (Exception e){
				log.error(e.getMessage());
				throw new SeckillException("seckill inner error:"+e.getMessage());
			}
		}else {
			log.error("exposer.isExposed is false");
		}
	}
	
	@Test
//	@Ignore
	public void executeSeckillProcedure(){
		long seckillId = 1000l;
		long phone = 13916012311l;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5 = exposer.getMd5();
			SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
			log.info(seckillExecution.getStateInfo());
		}
	}
}
