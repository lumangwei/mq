package com.lumang.mq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
    private final static String FANOUT_EXCHANGE_NAME = "FANOUT_SIMPLE_EXCHANGE";
    private final static String TOPIC_EXCHANGE_NAME = "TOPIC_EXCHANGE_NAME";

    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.147.157");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("weizhepeng");
        factory.setPassword("2803283409d");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // channel.exchangeDeclare(EXCHANGE_NAME,"direct",false,false,null);
        //channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, "fanout", false, false, null);
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic", false, false, null);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("waiting for message");
      //  channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "lumang.test");
       // channel.queueBind(QUEUE_NAME, FANOUT_EXCHANGE_NAME, "lumang.test");
        channel.queueBind(QUEUE_NAME, TOPIC_EXCHANGE_NAME, "#.lumang.test.*");
        Consumer consumer = new Consumer() {
            @Override
            public void handleConsumeOk(String s) {

            }

            @Override
            public void handleCancelOk(String s) {

            }

            @Override
            public void handleCancel(String s) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String s, ShutdownSignalException e) {

            }

            @Override
            public void handleRecoverOk(String s) {

            }

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String msg = new String(bytes, "UTF-8");
                System.out.println("Received message :" + msg);
                System.out.println("consumerTag:" + s);
                System.out.println("deliveryTag:" + envelope.getDeliveryTag());
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
