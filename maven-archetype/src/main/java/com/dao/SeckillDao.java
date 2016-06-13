package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.Seckill;


/**
 * 
 * @author hanliang
 * 秒杀的接口
 * 在mybatis没有指定parameterType的时候，通过@Param("xx")用来指定参数名。
 * 
 */
public interface SeckillDao {

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
	int reduceNumber(@Param("seckill_id") long seckill_id, @Param("killTime") Date killTime);
	
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
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	public void killByProcedure(Map<String,Object> paramMap);
}
