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
		//��������֪�������߷��͵ľ���һ�����ı���Ϣ�������������ֱ�ӽ���ǿ��ת��
        TextMessage textMsg = (TextMessage) message;
        System.out.println("Topicͨ�� ���յ�һ�����ı���Ϣ��");
        try {
            System.out.println("Topicͨ�� ��Ϣ�����ǣ�" + textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        
	}

}
