

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.ArticleOperation;
import com.dao.UserOperation;
import com.entity.Article;
import com.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
public class Mybatis {
	
	private Reader reader;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	private ObjectMapper mapper;
	
	@Autowired
	@Qualifier("articleMapper")
	private ArticleOperation articleMapper;
	
	@Autowired
	@Qualifier("userMapper")
	private UserOperation userMapper;
	
	@Before
	public void init(){
		mapper = new ObjectMapper(); 
//		try {
//			reader = Resources.getResourceAsReader("Configuration.xml");
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			session = sqlSessionFactory.openSession();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@After
	public void destroy(){
		mapper = null;
//		try {
//			mapper = null;
//			session.close();
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Test
	@Ignore
	public void testSelectUserByID() {
		
		UserOperation uo = session.getMapper(UserOperation.class);
		User user = uo.selectUserByID(1);
//		User user = session.selectOne("com.dao.UserOperation.selectUserByID",1);
		try {
			System.out.println(mapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void selectUsersByName() {
		
		List<User> user = (List<User>)session.getMapper(UserOperation.class).selectUsersByName("summer");
//		List user = (List) session.selectList("com.dao.UserOperation.selectUsersByName");
		String jsonString = null;
		for (int i = 0; i < user.size(); i++) {
			User us = user.get(i);
			try {
				jsonString = mapper.writeValueAsString(us);
				System.out.println("obj¡újson: "+ jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		try {
			User u = mapper.readValue(jsonString, User.class);
			jsonString = mapper.writeValueAsString(u);
			System.out.println("json¡úobj¡újson: "+jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void insertUser(){
		User user = new User();
		user.setUserName("winter");
		user.setUserAge("100");
		user.setUserAddress("modu,sh");
		session.getMapper(UserOperation.class).insertUser(user);
		session.commit();
	}

	@Test
	@Ignore
	public void updateUser(){
		User user = getUser();
		user.setUserAddress("ops,poe");
		session.getMapper(UserOperation.class).updateUser(user);
		session.commit();
	}
	
	private User getUser(){
		List<User> users = null;
		users = session.getMapper(UserOperation.class).selectUsersByName("winter");
		return users.get(0);
	}
	
	@Test
	@Ignore
	public void deleteUser(){
		User user = getUser();
		int id = user.getId();
		session.getMapper(UserOperation.class).deleteUser(id);
		session.commit();
	}
	
	@Test
	@Ignore
	public void testGetUserArticles(){
		List<Article> li = session.getMapper(ArticleOperation.class).getUserArticles(1);
		for(Article article: li){
			try {
				System.out.println(mapper.writeValueAsString(article));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	@Ignore
	public void testSpring(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
//		ArticleOperation articleOperation = (ArticleOperation)ctx.getBean("articleMapper"); 
		ArticleOperation articleOperation = articleMapper; 
		List<Article> li = articleOperation.getUserArticles(1);
		for(Article article : li){
			try {
				System.out.println(mapper.writeValueAsString(article));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		//Object way
		User u = new User();
		u.setUserName("summer");
		u.setUserAge("100");
		UserOperation userOperation = userMapper;
		List<User> uli = userOperation.selectUsersByName(u);
		//string way
//		String parameter = null;
//		parameter="summer";
//		List<User> uli = userOperation.selectUsersByName(parameter);
		for (User user : uli) {
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	@Ignore
	public void testChooseSelectUsersByName(){
		User u = new User();
		u.setUserName("summer");
		UserOperation userOperation = userMapper;
		List<User> uli = userOperation.chooseSelectUsersByName(u);
		for (User user : uli) {
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	@Ignore
	public void testTrimSelectUserByName(){
		User u = new User();
//		u.setUserName("summer");
		u.setUserAge("100");
		UserOperation userOperation = userMapper;
		List<User> uli = userOperation.trimSelectUserByName(u);
		for (User user : uli) {
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	@Ignore
	public void testForeachListSelectUserById(){
		List<String> li = new ArrayList<String>();
		String id = "1";
		String id2 = "2";
		li.add(id);
		li.add(id2);
		UserOperation userOperation = userMapper;
		List<User> uli = userOperation.foreachListSelectUserById(li);
		for (User user : uli) {
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	@Ignore
	public void testForeachArraySelectUserById(){
		String[] ids = {"1","2"};
		UserOperation userOperation = userMapper;
		List<User> uli = userOperation.foreachArraySelectUserById(ids);
		for (User user : uli) {
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
//	@Ignore
	public void testForeachMapSelectUserById(){
		Map<String,Object> map = new HashMap<String,Object>();
		
//		List<String> li = new ArrayList<String>();
//		String id = "1";
//		String id2 = "2";
//		li.add(id);
//		li.add(id2);
//		map.put("ids", li);
		
		String[] ids = {"1","2"};
		map.put("ids", ids);
		
		UserOperation userOperation = userMapper;
		List<User> uli = userOperation.foreachMapSelectUserById(map);
		for (User user : uli) {
			try {
				System.out.println(mapper.writeValueAsString(user));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
}
