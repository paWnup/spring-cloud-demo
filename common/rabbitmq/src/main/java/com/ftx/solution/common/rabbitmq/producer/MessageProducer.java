package com.ftx.solution.common.rabbitmq.producer;

import com.ftx.solution.common.base.util.SpringContextUtils;
import com.ftx.solution.common.rabbitmq.callback.MqCallback;
import com.ftx.solution.common.rabbitmq.util.MessageCache;
import com.ftx.solution.common.rabbitmq.util.MessageWithId;
import com.ftx.solution.common.rabbitmq.util.TimedMessage;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import java.util.UUID;

/**
 * 实际的消息生产者，兼顾回调
 *
 * @author puan
 * @date 2018-11-19 16:17
 **/
@Log4j
public class MessageProducer {

    /**
     * 发送消息到指定exchange，并通过指定routingkey到queue
     *
     * @param content  消息内容
     * @param exchange 发送消息到指定exchange
     * @param routing  并通过指定routingkey到queue
     */
    public void sendMessage(String content, String exchange, String routing) {
        String id = UUID.randomUUID().toString();
        sendMessage(new MessageWithId(id, content), exchange, routing);
    }

    /**
     * 重新发送消息
     *
     * @param timedMessage 消息
     */
    public void resendMessage(TimedMessage timedMessage) {
        sendMessage(timedMessage.getMessageWithId(), timedMessage.getExchange(), timedMessage.getRouting());
    }

    /**
     * 发送消息
     *
     * @param messageWithId 消息内容+ID
     * @param exchange      发送消息到指定exchange
     * @param routing       通过指定routingkey到queue
     */
    private void sendMessage(MessageWithId messageWithId, String exchange, String routing) {
        //缓存消息
        MessageCache.add(TimedMessage.bulidMessage(messageWithId, exchange, routing));
        getTemplate().convertAndSend(exchange, routing, new Gson().toJson(messageWithId), new CorrelationData(messageWithId.getId()));
    }

    /**
     * 每次发送消息都需要不同的template和callback以持有发送的消息
     *
     * @return RabbitTemplate
     */
    private RabbitTemplate getTemplate() {
        RabbitTemplate rabbitTemplate = SpringContextUtils.getContext().getBean(RabbitTemplate.class);
        MqCallback callback = SpringContextUtils.getContext().getBean(MqCallback.class);
        rabbitTemplate.setConfirmCallback(callback);
        rabbitTemplate.setReturnCallback(callback);
        //设置为true，才会return回调
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

}
