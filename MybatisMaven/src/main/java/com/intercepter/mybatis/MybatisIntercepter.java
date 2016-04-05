package com.intercepter.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.alibaba.fastjson.JSON;
import com.entity.Pagination;
import com.entity.SearchParam;

@Intercepts(value = { @Signature(args = { Connection.class }, method = "prepare", type = StatementHandler.class) })
public class MybatisIntercepter implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//获取代理对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		//MetaObject metaObject = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory() );
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory() );
		//获取sql对象
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//获取sql对象的id
		String id = mappedStatement.getId();
		System.out.println("sql id "+id);
		//进行拦截
		if(id.endsWith("SpecialMessages") ){
			//得到原来的sql
			BoundSql boundSql = statementHandler.getBoundSql();
//			System.out.println("boundSql "+boundSql.getSql());
			//获取输入的参数
			Object parameter = boundSql.getParameterObject();
			System.out.println(JSON.toJSONString(parameter));
			//分页patr1，获取总数据条数
			String totalCounts = "select count(1) from ("+boundSql.getSql()+") a";
			//获取connection
			Connection connection = (Connection) invocation.getArgs()[0];
			//准备prepared sql
			PreparedStatement preparedStatement = connection.prepareStatement(totalCounts);
			//为prepared sql 添加参数
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			System.out.println("parameterHandler "+JSON.toJSONString(parameterHandler));
			parameterHandler.setParameters(preparedStatement);
			//执行sql，获取结果
			ResultSet rs = preparedStatement.executeQuery();
			Pagination pagination = new Pagination();
			while(rs.next()){
				pagination.setTotalCounts(rs.getInt(1));
			}
			System.out.println("pagination "+JSON.toJSONString(pagination));
			//改造sql，使其带上分页
			String pageSql = boundSql.getSql() + " limit " + pagination.getBaseSearch() + "," + pagination.getBasePagi();
			System.out.println(pageSql);
			//更新sql
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		
		if(id.matches(".+ByPage$")){
			BoundSql boundSql = statementHandler.getBoundSql();
			//实际执行sql
			System.out.println(boundSql.getSql());
			Connection conn = (Connection) invocation.getArgs()[0];
			//获取分页总数据条数
			String countsSql = "select count(1) from ("+boundSql.getSql()+") a";
			PreparedStatement pstmt = conn.prepareStatement(countsSql);
			//获取配套参数
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			SearchParam searchParam = (SearchParam) parameterHandler.getParameterObject();
			System.out.println("searchParam "+JSON.toJSONString(searchParam));
			parameterHandler.setParameters(pstmt);
			ResultSet rs = pstmt.executeQuery();
			//分页设置
			Pagination pagination = new Pagination();
			while(rs.next()){
				pagination.setCurrentPage(searchParam.getPageNum());
				pagination.setTotalCounts(rs.getInt(1));
			}
			String sql = boundSql.getSql() + " limit "+ pagination.getBaseSearch()+","+pagination.getBasePagi();
			System.out.println("sql "+sql);
			//重设sql
			metaObject.setValue("delegate.boundSql.sql", sql);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
