package com.dao;

import com.entity.SuccessKilled;

/**
 * 
 * @author hanliang
 * 秒杀成功后记录数据的接口
 */
public interface SuccessKilledDao {

	/**
	 * 记录购买明细
	 * @param seckill_id
	 * @param user_phone
	 * @return
	 */
	int insertSuccessKilled(long seckill_id, long user_phone);
	
	/**
	 * 根据id查询SuccessKilled对象， 其中包含Seckill秒杀商品对象
	 * @param seckill_id
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(long seckill_id);
}
