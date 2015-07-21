
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.CategoryMapper;
import com.entity.Category;


public class MybatisGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void test() {
		List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "MybatisConfiguration.xml";
        File configFile = new File(MybatisGeneratorTest.class.getResource(genCfg).getFile());
        System.out.println(configFile.toURI());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
	}

	@Test
//	@Ignore 
	public void testCountByExample(){
		ObjectMapper mapper = new ObjectMapper();
		Reader reader;
		SqlSessionFactory sqlSessionFactory;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory  = new SqlSessionFactoryBuilder().build(reader);
			session = sqlSessionFactory.openSession();
			CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);

			Category categoryInsert = new Category();
//			categoryInsert.setId(1);
			categoryInsert.setCatname("catname");
			categoryInsert.setCatdescription("catdescription");
			categoryMapper.insertSelective(categoryInsert);
			session.commit();
			
			Category category = categoryMapper.selectByPrimaryKey(1);
			System.out.println(mapper.writeValueAsString(category));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
