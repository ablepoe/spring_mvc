package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dao.QueryDao;
import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;
import com.entity.Pagination;
import com.entity.SearchParam;
import com.service.QueryService;

/**
 * 
 * @author hanliang
 * Query服务实现类，调用Query数据库访问接口
 */
@Service("QueryService")
public class QueryServiceImpl implements QueryService{
	
	@Resource(name="QueryDao")
	private QueryDao queryDaoImpl;

//	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int getMessageTotalCounts() {
		return queryDaoImpl.getMessageTotalCounts();
	}
	
//	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insertCommandContent(CommandContent commandContent) {
		queryDaoImpl.insertCommandContent(commandContent);
	}

	
	@Override
	public List<Message> getAllMessages() {
		return queryDaoImpl.getAllMessages();
	}

	@Override
	public List<Message> getSpecialMessages(Command command) {
		return queryDaoImpl.getSpecialMessages(command);
	}
	
	@Override
	public boolean insertCommands(List<Command> commands) {
		int i = queryDaoImpl.insertCommands(commands);
		if(i == commands.size()){
			return true;	
		}else{
			return false;
		}
		
	}

	@Override
	public boolean removeOneCommand(int id) {
		int i = queryDaoImpl.removeOneCommand(id);
		System.out.println(i);
		if(i == 1){
			return true;
		}else{
			return false;	
		}
	}

	@Override
	public boolean removeSelected(List<String> ids) {
		int i = queryDaoImpl.removeSelected(ids);
		if(i == ids.size()){
			return true;
		}else{
			return false;	
		}
	}

	@Override
	public boolean updateOneCommand(List<Command> commands) {
		if(commands.size() != 2){
			return false;
		}
		if(commands.get(1).getCommand() == null || commands.get(1).getDescription() == null ){
			return false;
		}
		if(commands.get(0).getCommand().equals(commands.get(1).getCommand()) && commands.get(0).getDescription().equals(commands.get(1).getDescription()) ){
			return false;
		}
		if("".equals(commands.get(1).getCommand().trim()) || "".equals(commands.get(1).getDescription().trim()) ){
			return false;
		}
		int i = queryDaoImpl.updateOneCommand(commands.get(1));
		if(i == 1){
			return true;
		}else{
			return false;	
		}
	}

	@Override
	public Map<String,Object> getLimitCommands(String pageNum) {
		int totalCounts = queryDaoImpl.getAllMessages().size();
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(Integer.parseInt(pageNum));
		pagination.setTotalCounts(totalCounts);
		System.out.println(JSON.toJSONString(pagination));
		List<Message> messageList = queryDaoImpl.getLimitCommands(pagination);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("messageList", messageList);
		map.put("pagination", pagination);
		return map;
	}

	@Override
	public int getSpecialMessageCounts(Command command) {
		return queryDaoImpl.getSpecialMessageCounts(command);
	}

	@Override
	public Map<String,Object> getSpecialMessagesByPage(SearchParam searchParam) {
		int counts = queryDaoImpl.getSpecialMessageCounts(searchParam.getCommand());
		List<Message> messageList = queryDaoImpl.getSpecialMessagesByPage(searchParam);
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(searchParam.getPageNum());
		pagination.setTotalCounts(counts);
		System.out.println(JSON.toJSONString(pagination));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("messageList", messageList);
		map.put("pagination", pagination);
		return map;
	}
}
