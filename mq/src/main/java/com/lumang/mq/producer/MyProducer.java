package com.lumang.mq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyProducer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    private final static String FANOUT_EXCHANGE_NAME = "FANOUT_SIMPLE_EXCHANGE";

    private final static String TOPIC_EXCHANGE_NAME = "TOPIC_EXCHANGE_NAME";


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.147.157");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("weizhepeng");
        factory.setPassword("2803283409d");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String msg1 = "lumang.test.1";
        String msg2 = "lumang.test";
        String msg3 = "a.lumang.test.1";
        // channel.basicPublish(EXCHANGE_NAME, "lumang.test", null, msg.getBytes());
        // channel.basicPublish(FANOUT_EXCHANGE_NAME, "lumang1.test", null, msg.getBytes());
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "lumang.test.1", null, msg1.getBytes());
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "lumang.test", null, msg2.getBytes());
        channel.basicPublish(TOPIC_EXCHANGE_NAME, "a.lumang.test.1", null, msg3.getBytes());
        channel.close();
        connection.close();
    }
}
