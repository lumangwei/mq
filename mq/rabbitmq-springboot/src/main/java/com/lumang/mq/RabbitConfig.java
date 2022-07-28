package com.lumang.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws Exception{
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUri("amqp://weizhepeng:2803283409d@172.16.147.157:5672");
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin amqpAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }

    @Bean("lmDirectExchange")
    public DirectExchange getDirectExchange(){
        return new DirectExchange("LM_DRIECT_EXCHANGE");
    }

    @Bean("lmFanoutExchange")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("LM_FANOUT_EXCHANGE");
    }

    @Bean("lmTopicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange("LM_TOPIC_EXCHANGE");
    }

    @Bean("firstQueue")
    public Queue getFirstQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 6000);
       return new Queue("LM_FIRST_QUEUE_FROM_SPRINGBOOT", false, false, true, args);
    }

    @Bean("secondQueue")
    public Queue getSecondQueue(){
        return new Queue("LM_SECOND_QUEUE_FROM_SPRINGBOOT");
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue(){
        return new Queue("LM_THIRD_QUEUE_FROM_SPRINGBOOT");
    }

    @Bean
    public Binding bindingFirst(@Qualifier("firstQueue") Queue queue,@Qualifier("lmDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("lumang.mq");
    }

    @Bean
    public Binding bindingSecond(@Qualifier("secondQueue") Queue queue,@Qualifier("lmTopicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("#.lumang.*");
    }

    @Bean
    public Binding bindingThird(@Qualifier("thirdQueue") Queue queue,@Qualifier("lmFanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
