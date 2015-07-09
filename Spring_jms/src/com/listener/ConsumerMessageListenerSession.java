package com.listener;
 
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;
 
@Component
public class ConsumerMessageListenerSession implements
        SessionAwareMessageListener<TextMessage> {
 
	@Autowired
	@Qualifier("topicDestination")
    private Destination topicDestination;
    
    public void onMessage(TextMessage message, Session session) throws JMSException {
        System.out.println("收到一条消息");
        System.out.println("消息内容是：" + message.getText());
        MessageProducer producer = session.createProducer(topicDestination);
        Message textMessage = session.createTextMessage("ConsumerMessageListenerSession。。。");
        producer.send(textMessage);
    }
 
}

