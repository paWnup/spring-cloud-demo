package com.ftx.solution.common.rabbitmq.listener;

/**
 * 消息监听器实现此接口
 *
 * @author puan
 */
public interface MqListener {

    /**
     * 处理消息
     *
     * @param message 消息
     */
    void onMessage(String message);
}
