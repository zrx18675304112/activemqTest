package com.xiong.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Spring_Queue_Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("spring-activemq.xml");
        Spring_Queue_Consumer consumer = (Spring_Queue_Consumer)classPathXmlApplicationContext.getBean("spring_Queue_Consumer",Spring_Queue_Consumer.class);
        String  receiveAndConvert = (String)consumer.jmsTemplate.receiveAndConvert();
        System.out.println("消费者收到的消息"+receiveAndConvert);
    }
}
