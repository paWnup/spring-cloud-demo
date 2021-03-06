package com.ftx.solution.common.rabbitmq.config;

import com.ftx.solution.common.rabbitmq.callback.MqCallback;
import com.ftx.solution.common.rabbitmq.producer.MessageProducer;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * RabbiMq配置
 * Broker：它提供一种传输服务，它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输。
 * Exchange：消息交换机，它指定消息按什么规则，路由到哪个队列。
 * Queue：消息的载体，每个消息都会被投到一个或多个队列。
 * Binding：绑定，它的作用就是把exchange和queue按照路由规则绑定起来。
 * Routing Key：路由关键字，exchange根据这个关键字进行消息投递。
 * vhost：虚拟主机，一个broker里可以有多个vhost，用作不同用户的权限分离。
 * Producer：消息生产者，就是投递消息的程序。
 * Consumer：消息消费者，就是接受消息的程序。
 * Channel：消息通道，在客户端的每个连接里，可建立多个channel。
 *
 * @author puan
 * @date 2018-11-16 10:20
 **/
@Configuration
@Log4j
public class MqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    /**
     * 工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 连接，必须是prototype类型，不然每次回调都是最后一个内容
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        //设置为true，才会return回调
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    /**
     * 消息生产者
     */
    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    /**
     * 消息回调
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MqCallback mqConfirmCallback() {
        return new MqCallback();
    }

}
