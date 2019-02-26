package com.ftx.solution.common.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器工具类
 *
 * @author puan
 * @date 2018-11-23 11:13
 **/
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {
        if (applicationContext == null) {
            throw new RuntimeException("容器尚未初始化完成！");
        }
        return applicationContext;
    }
}
