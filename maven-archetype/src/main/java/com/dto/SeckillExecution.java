package com.dto;

import com.entity.SuccessKilled;
import com.enums.SeckillStateEnum;

/**
 * 
 * @author hanliang
 * 秒杀执行后的结果
 */
public class SeckillExecution {

	/**
	 * 秒杀对象id
	 */
	private long seckillId;
	
	/**
	 * 秒杀结果状态
	 */
	private int state;
	
	/**
	 * 秒杀结果标示
	 */
	private String stateInfo;
	
	/**
	 * 秒杀成功对象
	 */
	private SuccessKilled successKilled;

	/**
	 * 秒杀成功
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 * @param successKilled
	 */
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	/**
	 * 秒杀失败
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 */
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getStateInfo();
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}
	
	
}
