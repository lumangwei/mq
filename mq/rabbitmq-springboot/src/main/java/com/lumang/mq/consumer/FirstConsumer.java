package com.lumang.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "LM_FIRST_QUEUE_FROM_SPRINGBOOT")
public class FirstConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("First Queue received msg:"+msg);
    }
}
