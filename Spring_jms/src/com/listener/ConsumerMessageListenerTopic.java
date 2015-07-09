package com.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMessageListenerTopic implements MessageListener{
	
	@Autowired
	@Qualifier("queueDestination")
	private Destination queueDestination;
	
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	@Override
	public void onMessage(Message message) {
		//这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMsg = (TextMessage) message;
        System.out.println("Topic通道 接收到一个纯文本消息。");
        try {
            System.out.println("Topic通道 消息内容是：" + textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        
	}

}
