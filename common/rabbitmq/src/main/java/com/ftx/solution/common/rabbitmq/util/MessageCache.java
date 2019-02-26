package com.ftx.solution.common.rabbitmq.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息缓存，用于缓存发送过的消息。
 * 若消息发送失败，则从此缓存中获取消息尝试重新发送；
 * 若消息发送成功，则删除缓存。
 *
 * @author puan
 * @date 2018-11-26 11:57
 **/
public class MessageCache {

    private static final Map<String, TimedMessage> CACHED_MESSAGE = new ConcurrentHashMap<>();

    public static void add(TimedMessage timedMessage) {
        CACHED_MESSAGE.put(timedMessage.getMessageWithId().getId(), timedMessage);
    }

    public static void delete(String id) {
        CACHED_MESSAGE.remove(id);
    }

    public static TimedMessage get(String id) {
        return CACHED_MESSAGE.get(id);
    }

    /**
     * 获取指定时间内未确认的消息
     *
     * @param interval 时间间隔
     * @param now      时间节点
     * @return 消息集合
     */
    public static List<TimedMessage> getNackMessages(int interval, long now) {
        List<TimedMessage> timedMessages = new ArrayList<>();
        for (String id : CACHED_MESSAGE.keySet()) {
            TimedMessage timedMessage = CACHED_MESSAGE.get(id);
            if (timedMessage != null && (timedMessage.getTime() + interval * 60000 < now)) {
                timedMessages.add(timedMessage);
            }
        }
        return timedMessages;
    }
}
