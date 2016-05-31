package com.service;

import java.util.List;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.entity.Seckill;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;

/**
 * 
 * @author hanliang
 * secKill的service接口
 */
public interface SeckillService {

	/**
	 * 获取全部秒杀对象
	 */
	public List<Seckill> getSeckillList();
	
	/**
	 * 获取指定id的秒杀对象
	 * @param seckill_id
	 * @return
	 */
	public Seckill getSeckillById(long seckill_id);
	
	/**
	 * 获取秒杀的地址，若秒杀未开始，则返回时间
	 * @param seckill_id
	 * @return
	 */
	public Exposer exportSeckillUrl(long seckill_id);
	
	/**
	 * 执行秒杀
	 * @param seckill_id
	 * @param user_phone
	 * @param md5
	 * @return
	 */
	public SeckillExecution executeSeckill(long seckill_id, long user_phone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
