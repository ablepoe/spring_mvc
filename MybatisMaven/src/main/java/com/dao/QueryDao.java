package com.dao;

import java.util.List;

import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;

/**
 * 
 * @author hanliang
 * Query数据库请求接口
 */
public interface QueryDao {
	
	public int getMessageTotalCounts();
	
	public void insertCommandContent(CommandContent commandContent);
	
	public List<Message> getAllMessages();
	
	public List<Message> getSpecialMessages(Command command);
}
