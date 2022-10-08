package com.xiong.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsummer_topic {
    public static final String ACTIVEMQ_URL = "tcp://47.92.141.98:61616";
    public static final String TOPIC_NAME = "jdbc01";
    public static final String UserName = "root";
    public static final String PassWord = "ZRX24681379";

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(UserName, PassWord, ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4 创建目的地 （两种 ： 队列/主题   这里用主题）
        Topic topic = session.createTopic(TOPIC_NAME);

        MessageConsumer messageConsumer = session.createConsumer(topic);
        // MessageListener接口只有一个方法，可以使用lambda表达式
        messageConsumer.setMessageListener((message) -> {
            if (null != message && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消息体：" + textMessage.getText());
                    System.out.println("消息属性：" + textMessage.getStringProperty("From"));
                    System.out.println("消息属性：" + textMessage.getByteProperty("Spec"));
                    System.out.println("消息属性：" + textMessage.getBooleanProperty("Invalide"));
                } catch (JMSException e) {
                }
            }
        });

        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
