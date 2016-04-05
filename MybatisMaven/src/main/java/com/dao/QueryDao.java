package com.dao;

import java.util.List;

import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;
import com.entity.Pagination;
import com.entity.SearchParam;

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
	
	public int insertCommands(List<Command> commands);
	
	public int removeOneCommand(int id);
	
	public int removeSelected(List<String> ids);
	
	public int updateOneCommand(Command command);
	
	public List<Message> getLimitCommands(Pagination pagination);
	
	public int getSpecialMessageCounts(Command command);
	
	public List<Message> getSpecialMessagesByPage(SearchParam searchParam);
}
