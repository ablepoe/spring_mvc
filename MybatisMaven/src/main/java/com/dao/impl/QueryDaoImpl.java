package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.QueryDao;
import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;
import com.entity.Pagination;
import com.entity.SearchParam;

/**
 * 
 * @author hanliang
 * Query数据库请求与实现类，实际与数据库进行交互
 */
@Repository("QueryDao")
public class QueryDaoImpl implements QueryDao {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public int getMessageTotalCounts() {
		//根据接口进行映射
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.getMessageTotalCounts();
	}

	@Override
	public void insertCommandContent(CommandContent commandContent) {
		//根据接口进行映射
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		queryDao.insertCommandContent(commandContent);
	}

	@Override
	public List<Message> getAllMessages() {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.getAllMessages();
	}

	@Override
	public List<Message> getSpecialMessages(Command command) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.getSpecialMessages(command);
	}

	@Override
	public int insertCommands(List<Command> commands) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.insertCommands(commands);
	}

	@Override
	public int removeOneCommand(int id) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.removeOneCommand(id);
	}

	@Override
	public int removeSelected(List<String> ids) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.removeSelected(ids);
	}

	@Override
	public int updateOneCommand(Command command) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.updateOneCommand(command);
	}

	@Override
	public List<Message> getLimitCommands(Pagination pagination) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.getLimitCommands(pagination);
	}

	@Override
	public int getSpecialMessageCounts(Command command) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.getSpecialMessageCounts(command);
	}

	@Override
	public List<Message> getSpecialMessagesByPage(SearchParam searchParam) {
		QueryDao queryDao = sqlSession.getMapper(QueryDao.class);
		return queryDao.getSpecialMessagesByPage(searchParam);
	}
}
