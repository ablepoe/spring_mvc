package com.listener;
 
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.service.ProducerService;
 
public class ConsumerMessageListener implements MessageListener {
	
    @Autowired
    private ProducerService producerService;
    
	@Autowired
	@Qualifier("topicDestination")
	private Destination topicDestination;
 
    public void onMessage(Message message) {
        //��������֪�������߷��͵ľ���һ�����ı���Ϣ�������������ֱ�ӽ���ǿ��ת��
        TextMessage textMsg = (TextMessage) message;
        System.out.println("���յ�һ�����ı���Ϣ��");
        try {
            System.out.println("��Ϣ�����ǣ�" + textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
}