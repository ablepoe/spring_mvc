package com.dhc.entity;

import java.io.Serializable;

/**
 * 
 * @author hanliang 20160617
 * -ajax return entity
 *
 */
public class ResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 3869228817459223065L;
	
	private String status;
	private String msgCode;
	private String msgContent;
	private Object data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
