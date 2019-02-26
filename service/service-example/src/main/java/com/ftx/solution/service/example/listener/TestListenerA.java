package com.ftx.solution.service.example.listener;

import com.ftx.solution.common.rabbitmq.ListenedQueue;
import com.ftx.solution.common.rabbitmq.listener.MqListener;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

/**
 * @author puan
 * @date 2018-11-21 17:41
 **/
@Component
@Log4j
@ListenedQueue("QUEUE_A")
public class TestListenerA implements MqListener {


    @Override
    public void onMessage(String message) {
        log.info("QUEUE_A监听器打印消息：" + message);
    }
}
