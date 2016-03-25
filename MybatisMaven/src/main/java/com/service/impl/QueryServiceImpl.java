package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.QueryDao;
import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;
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
}
