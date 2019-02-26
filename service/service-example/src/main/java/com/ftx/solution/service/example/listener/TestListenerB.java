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
@ListenedQueue("QUEUE_B")
@Log4j
public class TestListenerB implements MqListener {


    @Override
    public void onMessage(String message) {
//        throw new BusinessException("业务异常");
        log.info("QUEUE_B监听器打印消息：" + message);
    }
}
