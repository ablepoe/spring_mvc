
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.entity.Command;
import com.entity.CommandContent;
import com.entity.Message;
import com.entity.SearchParam;
import com.service.QueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class QueryTest {

	@Resource(name="QueryService")
	private QueryService queryService;
	
	@Test
	@Ignore
	public void testGetMessageTotalCounts() {
		System.out.println(queryService.getMessageTotalCounts());
	}
	
	@Test
	@Ignore
	public void testTransactional(){
		CommandContent commandContent = new CommandContent();
		commandContent.setContent("test");
		commandContent.setCommand_id("1");
		queryService.insertCommandContent(commandContent);
	}
	
	@Test
	@Ignore
	public void testGetAllMessage(){
		List<Message> li = queryService.getAllMessages();
		for (Message message : li) {
			System.out.println(JSON.toJSONString(message));
		}
	}
	
	@Test
	public void testPagination(){
		Command command = new Command();
		command.setCommand("");
		command.setDescription("测试");
		SearchParam searchParam = new SearchParam();
		searchParam.setPageNum(2);
		searchParam.setCommand(command);
		Map<String,Object> map = queryService.getSpecialMessagesByPage(searchParam);
		System.out.println(JSON.toJSONString(map));
	}
}
