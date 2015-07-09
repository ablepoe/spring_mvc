package test;
 
import javax.jms.Destination;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.ProducerService;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProducerConsumerTest {
 
    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination queueDestination;
    
    @Autowired
    @Qualifier("topicDestination")
    private Destination topicDestination;
    
    @Autowired
    @Qualifier("sessionDestination")
    private Destination sessionDestination;
    
    @Autowired
    @Qualifier("adapterDestination")
    private Destination adaptDestination;
    
    @Test
    @Ignore
    public void testSendQueue() {
        producerService.sendMessage(queueDestination, "你好，生产者！这是消息：" + (1));
    }
    
    @Test
    @Ignore
    public void testSendTopic(){
    	producerService.sendMessage(topicDestination, "LALALALALA");
    }
    
    @Test
    @Ignore
    public void testSendSession(){
    	producerService.sendMessage(sessionDestination, "HAHAHAHA");
    }
    
    @Test  
//    @Ignore
    public void testMessageListenerAdapter() {  
        producerService.sendMessage(adaptDestination, "测试MessageListenerAdapter");  
    }  
}
