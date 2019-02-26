package com.ftx.solution.management.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author puan
 * @date 2018-11-21 16:12
 **/
@Configuration
public class MqConfig {

    public static final String EXCHANGE_A = "my-mq-exchange_A";

    public static final String QUEUE_A = "QUEUE_A";

    public static final String QUEUE_B = "QUEUE_B";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";

    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";

    /**
     * 针对消费者配置
     * 设置交换机类型
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取队列A
     */
    @Bean
    public Queue queueA() {
        //ture表示持久化队列
        return new Queue(QUEUE_A, true);
    }

    @Bean
    public Queue queueB() {
        //ture表示持久化队列
        return new Queue(QUEUE_B, true);
    }

    /**
     * 将队列绑定到交换机上
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(ROUTINGKEY_A);
    }

    /**
     * 将队列绑定到交换机上
     */
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(ROUTINGKEY_B);
    }
}
