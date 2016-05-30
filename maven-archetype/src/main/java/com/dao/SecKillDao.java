package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.Seckill;


/**
 * 
 * @author hanliang
 * 秒杀的接口
 */
public interface SecKillDao {

	//方法前不加声明为默认 DEFALT 
	/*
	作用域    当前类  同包 子类 其他
	public        √        √       √      √
	protected     √        √       √      ×
	default       √        √       ×      ×
	private       √        ×       ×      ×
	*/
	/**
	 * 秒杀减库存
	 * @param seckill_id
	 * @param killTime
	 * @return 执行后数据库更新的记录数
	 */
	int reduceNumber(long seckill_id, Date killTime);
	
	/**
	 * 根据id查询秒杀对象
	 * @param seckill_id
	 * @return
	 */
	Seckill queryById(long seckill_id);
	
	/**
	 * 根据偏移量，查询秒杀对象列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(int offset, int limit);
	
	
}
