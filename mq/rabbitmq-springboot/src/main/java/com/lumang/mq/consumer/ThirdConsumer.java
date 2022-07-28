package com.lumang.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "LM_THIRD_QUEUE_FROM_SPRINGBOOT")
public class ThirdConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("Third Queue received msg:"+msg);
    }
}
