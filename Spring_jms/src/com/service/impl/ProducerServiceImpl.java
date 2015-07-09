package com.service.impl;
 
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
 



import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.service.ProducerService;
 
 
@Component
public class ProducerServiceImpl implements ProducerService {
 
	@Autowired
	@Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;
	
    /*@Override
    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    } */
    
    @Autowired  
    @Qualifier("responseDestination")  
    private Destination responseDestination;  
      
    public void sendMessage(Destination destination, final String message) {  
        System.out.println("---------------生产者发送消息-----------------");  
        System.out.println("---------------生产者发了一个消息：" + message);  
        jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                TextMessage textMessage = session.createTextMessage(message);  
                textMessage.setJMSReplyTo(responseDestination);  
                return textMessage;  
            }  
        });  
    }  

}
