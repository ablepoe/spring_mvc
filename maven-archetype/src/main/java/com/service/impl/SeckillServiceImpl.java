package com.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.dao.SeckillDao;
import com.dao.SuccessKilledDao;
import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.entity.Seckill;
import com.entity.SuccessKilled;
import com.enums.SeckillStateEnum;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;
import com.service.SeckillService;

/**
 * 
 * @author hanliang
 * SeckillService的具体实现类
 */
@Service("SeckillService")
public class SeckillServiceImpl implements SeckillService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	private static int OFFSET = 0;
	
	private static int LIMIT = 100;
	
	private static String SALT = "thisismytempsalt";

	@Resource
	private SeckillDao seckillDao;
	
	@Resource
	private SuccessKilledDao successKilledDao;

	/**
	 * 获取全部秒杀对象
	 * (有偏移量)
	 */
	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(OFFSET, LIMIT);
	}

	/**
	 * 获取指定id的秒杀对象
	 */
	@Override
	public Seckill getSeckillById(long seckill_id) {
		return seckillDao.queryById(seckill_id);
	}

	/**
	 * 暴露秒杀地址
	 */
	@Override
	public Exposer exportSeckillUrl(long seckill_id) {
		//验证秒杀对象存在
		Seckill seckill = seckillDao.queryById(seckill_id);
		if(seckill == null){
			return new Exposer(false, seckill_id);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		//不在秒杀时间段内
		if(nowTime.before(startTime) || nowTime.after(endTime)){
			//返回系统时间
			return new Exposer(false, seckill_id, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		//获取md5
		String md5 = getMd5(seckill_id);
		return new Exposer(true, md5, seckill_id);
	}
	
	/**
	 * 秒杀功能实现
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public SeckillExecution executeSeckill(long seckill_id, long user_phone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if(md5 == null || !md5.equals(getMd5(seckill_id))){
			return new SeckillExecution(seckill_id, SeckillStateEnum.DATA_REWRITE);
		}
		try{
			//秒杀逻辑
			//先减少数量
			//在插入履历
			Date killTime = new Date();
			int updateCount = seckillDao.reduceNumber(seckill_id, killTime);
			if(updateCount <= 0){
				throw new SeckillCloseException("seckill is closed");
			}else{
				int insertCount = successKilledDao.insertSuccessKilled(seckill_id, user_phone);
				if(insertCount <= 0){
					//秒杀失败
					throw new RepeatKillException("seckill repeated");
				}else{
					//秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckill_id, user_phone);
					return new SeckillExecution(seckill_id, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1){
			throw e1;
		} catch (RepeatKillException e2){
			throw e2;
		} catch (SeckillException e){
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}

	/**
	 * 生成md5
	 * @param seckill_id
	 * @return
	 */
	private String getMd5(long seckill_id){
		String source = seckill_id + SALT;
		String md5 = DigestUtils.md5DigestAsHex(source.getBytes());
		return md5;
	}

	@Override
	public SeckillExecution executeSeckillProcedure(long seckill_id,
			long user_phone, String md5) throws SeckillException,
			RepeatKillException, SeckillCloseException {
		if(md5 == null || !md5.equals(getMd5(seckill_id))){
			return new SeckillExecution(seckill_id, SeckillStateEnum.DATA_REWRITE);
		}
		Date killTime = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("seckillId", seckill_id);
		map.put("phone", user_phone);
		map.put("killTime", killTime);
		map.put("result", null);
		try{
			seckillDao.killByProcedure(map);
			int result = MapUtils.getInteger(map, "result",-2);
			if(result == 1){
				SuccessKilled sk = successKilledDao.queryByIdWithSeckill(seckill_id, user_phone);
				return new SeckillExecution(seckill_id, SeckillStateEnum.SUCCESS,sk);
			}else{
				return new SeckillExecution(seckill_id, SeckillStateEnum.stateOf(result));
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return new SeckillExecution(seckill_id, SeckillStateEnum.INNER_ERROR);
		}
	}
}
