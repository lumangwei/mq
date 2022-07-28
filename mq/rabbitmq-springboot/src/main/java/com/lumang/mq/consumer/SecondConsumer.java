package com.lumang.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "LM_SECOND_QUEUE_FROM_SPRINGBOOT")
public class SecondConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("Decond Queue received msg:"+msg);
    }
}
