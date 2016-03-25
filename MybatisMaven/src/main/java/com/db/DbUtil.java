package com.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dao.QueryDao;

/**
 * 
 * @author hanliang
 * 非spring方法的sqlSession获取
 */
public class DbUtil {
	
	private Reader reader;
	private SqlSessionFactory sqlSessionFactory;
	//设置protected为了能让子类继承
	private SqlSession sqlSession;

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public SqlSession getSqlSession(){
		if(sqlSession != null){
			return sqlSession;
		}
		try {
			//1、读取配置文件
			reader = Resources.getResourceAsReader("com/config/Configuration.xml");
			//2、建立工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			//3、开启session
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		dbUtil.getSqlSession();
		int i = dbUtil.getSqlSession().getMapper(QueryDao.class).getMessageTotalCounts();
		System.out.println(i);
	}
}
