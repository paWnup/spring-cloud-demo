package com.ftx.solution.common.rabbitmq;

import com.ftx.solution.common.rabbitmq.listener.MqListener;

import java.lang.annotation.*;

/**
 * 此注解用于标注实现了{@code MqListener}的类，用于标记监听的队列名称
 * 当队列中有消息是，会调用实现类的onMessage方法
 *
 * @see MqListener
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListenedQueue {
    /**
     * 监听的队列名称
     */
    String value() default "";
}
