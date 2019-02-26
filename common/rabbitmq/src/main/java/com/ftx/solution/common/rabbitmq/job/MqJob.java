package com.ftx.solution.common.rabbitmq.job;

import com.ftx.solution.common.rabbitmq.producer.MessageProducer;
import com.ftx.solution.common.rabbitmq.util.MessageCache;
import com.ftx.solution.common.rabbitmq.util.TimedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 启动定时任务，定时从此缓存中取出未被确认的消息重新发送，需要消费端做消息幂等。
 * <p>
 * 此方案只能解决由于网络问题造成的消息发送不成功。
 * 若是因为MQ的问题（如Exchange不存在、Routing不存在或者Queue不存在）造成的消息发送不成功，
 * 那么一直重复发送消息也于事无补。
 * 所以，若是MQ造成的问题，需要对客户端进行提醒并做响应处理。
 *
 * @author puan
 * @date 2018-11-26 14:13
 **/
@Component
public class MqJob {

    @Value("${spring.rabbitmq.interval}")
    private int interval;

    @Autowired
    private MessageProducer msgProducer;

    /**
     * 定时重发消息
     * 间隔时间：一分钟
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void startResend() {
        List<TimedMessage> timedMessages = MessageCache.getNackMessages(interval, System.currentTimeMillis());
        for (TimedMessage timedMessage : timedMessages) {
            MessageCache.delete(timedMessage.getMessageWithId().getId());
            msgProducer.resendMessage(timedMessage);
        }
    }

}
