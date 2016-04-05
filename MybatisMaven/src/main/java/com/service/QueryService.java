package com.service;

import java.util.List;
import java.util.Map;

import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;
import com.entity.SearchParam;

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
	
	public boolean insertCommands(List<Command> commands);
	
	public boolean removeOneCommand(int id);
	
	public boolean removeSelected(List<String> ids);
	
	public boolean updateOneCommand(List<Command> command);
	
	public Map<String,Object> getLimitCommands(String pageNum);
	
	public int getSpecialMessageCounts(Command command);
	
	public Map<String,Object> getSpecialMessagesByPage(SearchParam searchParam);
}
