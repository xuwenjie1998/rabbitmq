package com.example.rabbitmq.compoent.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    // 配置一个工作模型队列
    @Bean
    public Queue queueWork1() {
        return new Queue("queue_work",true);
    }

    @Bean
    public Queue queueOrder() {
        return new Queue("order.ensure");
    }

    @Bean
    public TopicExchange creTopicExchange(){
        return new TopicExchange("topicTest",true,false);
    }

    @Bean
    public Binding creBinding(){
        return BindingBuilder.bind(queueOrder()).to(creTopicExchange()).with("spring.*");
    }
}

