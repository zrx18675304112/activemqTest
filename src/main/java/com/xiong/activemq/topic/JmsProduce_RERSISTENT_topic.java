package com.xiong.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_RERSISTENT_topic {
    public static final String ACTIVEMQ_URL = "tcp://47.92.141.98:61616";
    public static final String TOPIC_NAME = "jdbc01";
    public static final String UserName = "root";
    public static final String PassWord = "ZRX24681379";

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(UserName, PassWord, ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topic);
        for (int i = 1; i < 4; i++) {
//            TextMessage textMessage = session.createTextMessage("topic_name--" + i);
//            // 这里可以指定每个消息的目的地
//            textMessage.setJMSDestination(topic);
//            /*
//            持久模式和非持久模式。
//            一条持久性的消息：应该被传送“一次仅仅一次”，这就意味着如果JMS提供者出现故障，该消息并不会丢失，它会在服务器恢复之后再次传递。
//            一条非持久的消息：最多会传递一次，这意味着服务器出现故障，该消息将会永远丢失。
//             */
//            textMessage.setJMSDeliveryMode(0);
//            /*
//            可以设置消息在一定时间后过期，默认是永不过期。
//            消息过期时间，等于Destination的send方法中的timeToLive值加上发送时刻的GMT时间值。
//            如果timeToLive值等于0，则JMSExpiration被设为0，表示该消息永不过期。
//            如果发送后，在消息过期时间之后还没有被发送到目的地，则该消息被清除。
//             */
//            textMessage.setJMSExpiration(1000);
//            /*  消息优先级，从0-9十个级别，0-4是普通消息5-9是加急消息。
//            JMS不要求MQ严格按照这十个优先级发送消息但必须保证加急消息要先于普通消息到达。默认是4级。
//             */
//            textMessage.setJMSPriority(10);
//            // 唯一标识每个消息的标识。MQ会给我们默认生成一个，我们也可以自己指定。
//            textMessage.setJMSMessageID("ABCD");
//            // 上面有些属性在send方法里也能设置
//            messageProducer.send(textMessage);
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("name", "张三" + i);
            mapMessage.setInt("age", 18 + i);
            messageProducer.send(mapMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("  **** TOPIC_NAME消息发送到MQ完成 ****");
    }
}
