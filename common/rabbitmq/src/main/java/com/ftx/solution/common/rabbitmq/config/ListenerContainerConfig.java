package com.ftx.solution.common.rabbitmq.config;

import com.ftx.solution.common.base.util.SpringContextUtils;
import com.ftx.solution.common.rabbitmq.ListenedQueue;
import com.ftx.solution.common.rabbitmq.listener.MqListener;
import com.ftx.solution.common.rabbitmq.util.MessageWithId;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 队列监听配置，所有监听从这里进入，在这里进行分配
 *
 * @author puan
 * @date 2018-11-23 14:15
 **/
@Component
@Log4j
@Import({MqConfig.class, SpringContextUtils.class})
public class ListenerContainerConfig {

    /**
     * 监听集合，将队列名名和监听器类一一对应。
     * 目前并未实现消息队列的动态监听，所以此集合在服务初始化的时候就已经创建好，并且不会再修改，
     * 所以不会出现多线程问题，因此并未使用ConcurrentHashMap。
     */
    private Map<String, MqListener> listenerMap = new HashMap<>(10);

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 监听容器，实例化优先级较低，需要先实例化队列bean
     */
    @Bean
    @Order(50)
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(getLisenterQueues());
        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
        //设置消息确认，实现失败重试功能
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener((ChannelAwareMessageListener) this::handleMessage);
        return container;
    }

    /**
     * 获取项目中所有监听的队列以及监听
     */
    private String[] getLisenterQueues() {
        ApplicationContext applicationContext = SpringContextUtils.getContext();
        Map<String, MqListener> listeners = applicationContext.getBeansOfType(MqListener.class);
        List<String> queueNames = new ArrayList<>();
        for (MqListener listener : listeners.values()) {
            ListenedQueue listenedQueue = listener.getClass().getAnnotation(ListenedQueue.class);
            String queueName = listenedQueue.value();
            log.info("注册队列监听，队列名称：" + queueName);
            queueNames.add(queueName);
            listenerMap.put(queueName, listener);
        }
        return queueNames.toArray(new String[]{});
    }

    /**
     * 消息处理，项目中所有的消息都进入到这个方法，并在这个方法进行分发做具体的业务处理。
     * 此方法封装消息的获取、日志打印、以及消息的失败重试。
     *
     * @throws IOException 连接rabbitMq失败
     */
    private void handleMessage(Message message, Channel channel) throws IOException {
        try {
            //TODO 日志打印以及消息存档

            //消息分发，业务实现
            MqListener listener = listenerMap.get(message.getMessageProperties().getConsumerQueue());
            MessageWithId messageWithId = new Gson().fromJson(new String(message.getBody()), MessageWithId.class);
            listener.onMessage(messageWithId.getMessage());

            // false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            //e.printStackTrace();
            failedAndRetry(message, channel);
        }
    }

    /**
     * 失败重试
     *
     * @throws IOException 连接rabbitMq失败
     */
    private void failedAndRetry(Message message, Channel channel) throws IOException {
        // deliveryTag是消息传送的次数，若消息处理时抛出异常，则将消息重新放回队列，处理失败3次则不再处理（消息从队列中删除）。
        if (message.getMessageProperties().getDeliveryTag() >= 3) {
            log.error("消息已重复处理失败，释放消息，不再处理，消息ID：" + message.getMessageProperties().getHeaders().get("spring_listener_return_correlation"));
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            log.info("消息即将再次返回队列处理，消息ID：" + message.getMessageProperties().getHeaders().get("spring_listener_return_correlation"));
            // requeue(b1)为是否重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

}