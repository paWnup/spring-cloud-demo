package com.ftx.solution.common.rabbitmq.util;

import lombok.Data;

/**
 * @author puan
 * @date 2018-11-26 14:20
 **/
@Data
public class TimedMessage {

    private MessageWithId messageWithId;

    private long time;

    private String exchange;

    private String routing;

    public static TimedMessage bulidMessage(MessageWithId messageWithId, String exchange, String routing) {
        TimedMessage timedMessage = new TimedMessage();
        timedMessage.setExchange(exchange);
        timedMessage.setMessageWithId(messageWithId);
        timedMessage.setRouting(routing);
        timedMessage.setTime(System.currentTimeMillis());
        return timedMessage;
    }

}
