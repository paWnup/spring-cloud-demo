package com.ftx.solution.management.example.config;

import com.ftx.solution.common.base.config.Oauth2Config;
import com.ftx.solution.common.base.config.Swagger2Config;
import com.ftx.solution.common.base.config.ValidationConfig;
import com.ftx.solution.common.base.handler.GlobalExceptionHandler;
import com.ftx.solution.common.base.util.SpringContextUtils;
import com.ftx.solution.common.rabbitmq.config.MqConfig;
import com.ftx.solution.common.rabbitmq.job.MqJob;
import com.ftx.solution.common.redis.config.RedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 组件配置类，用于加载在common模块下面的配置
 * <p>
 * springboot默认扫描的包是启动类所在的包，因此其他的配置需要手动引入。
 *
 * @author puan
 * @date 2018-11-27 15:41
 **/
@Configuration
@Import({Swagger2Config.class,
        ValidationConfig.class,
        GlobalExceptionHandler.class,
        Oauth2Config.class,
        RedisConfig.class,
        MqConfig.class,
        MqJob.class,
        SpringContextUtils.class})
public class ComponentConfig {

}
