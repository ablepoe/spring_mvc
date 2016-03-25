package com.service;

import java.util.List;

import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;

/**
 * 
 * @author hanliang
 * Query服务接口
 */
public interface QueryService {

	public int getMessageTotalCounts();
	
	public void insertCommandContent(CommandContent commandContent);
	
	public List<Message> getAllMessages();
	
	public List<Message> getSpecialMessages(Command command);
}
