package com.ftx.solution.common.rabbitmq.callback;

import com.ftx.solution.common.rabbitmq.util.MessageCache;
import com.ftx.solution.common.rabbitmq.util.MessageWithId;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 消息回调
 *
 * @author puan
 * @date 2018-11-27 16:13
 **/
@Log4j
public class MqCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    /**
     * 消息确认回调，用于判断消息是否到达exchange
     * 如果消息到达exchange，则ack=true，否则ack=false
     *
     * @param correlationData 消息标识
     * @param ack             标记消息是否成功消费
     * @param cause           失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.error("confirm回调，消息发送失败，将尝试重新发送，id:" + correlationData.getId() + ",ack:false,cause:" + cause);
        } else {
            MessageCache.delete(correlationData.getId());
        }
    }

    /**
     * exchange到queue成功,则不回调return
     * 若消息从exchange到queue失败，则回调return(需设置mandatory=true，否则不回回调)
     *
     * @param message  消息
     * @param code     错误码
     * @param cause    原因
     * @param exchange exchange名称
     * @param routing  routing名称
     */
    @Override
    public void returnedMessage(Message message, int code, String cause, String exchange, String routing) {
        MessageWithId messageWithId = new Gson().fromJson(new String(message.getBody()), MessageWithId.class);
        log.error("return回调，消息发送失败，id:" + messageWithId.getId() + "code:" + code + ", cause:" + cause + "exchange:" + exchange + "routing:" + routing + "message:" + message);
    }

}
