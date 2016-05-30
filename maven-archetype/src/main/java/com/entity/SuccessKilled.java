package com.entity;

import java.util.Date;

/**
 * 
 * @author hanliang
 * 数据库表success_killed的实体对应
 */
public class SuccessKilled {

	private long seckillId;
	
	private long user_phone;
	
	private int state;
	
	private Date createTime;
	//包含秒杀实体对象
	private Seckill seckill;
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public long getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(long user_phone) {
		this.user_phone = user_phone;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Seckill getSeckill() {
		return seckill;
	}
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", user_phone="
				+ user_phone + ", state=" + state + ", createTime="
				+ createTime + ", seckill=" + seckill + "]";
	}

	
	
}
