package com.listener;
 
public class ConsumerListener {
 
    public void handleMessage(String message) {
        System.out.println("ConsumerListenerͨ��handleMessage���յ�һ�����ı���Ϣ����Ϣ�����ǣ�" + message);
    }
    
   /* public void receiveMessage(String message) {
        System.out.println("ConsumerListenerͨ��receiveMessage���յ�һ�����ı���Ϣ����Ϣ�����ǣ�" + message);
    }*/
    
    public String receiveMessage(String message) {  
        System.out.println("ConsumerListenerͨ��receiveMessage���յ�һ�����ı���Ϣ����Ϣ�����ǣ�" + message);  
        return "����ConsumerListener�����receiveMessage�����ķ���ֵ��";  
    }  
    
}

