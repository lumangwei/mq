package com.lumang.mq.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@ComponentScan(basePackages = "com.lumang.mq")
public class MyProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyProducer.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("","LM_FIRST_QUEUE_FROM_SPRINGBOOT","a direct msg");
        rabbitTemplate.convertAndSend("LM_TOPIC_EXCHANGE","a.lumang.b","a topic msg");
        rabbitTemplate.convertAndSend("LM_FANOUT_EXCHANGE", "lumang", "a fanout exchange");
    }
}
