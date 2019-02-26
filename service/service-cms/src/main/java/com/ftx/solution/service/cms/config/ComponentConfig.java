package com.ftx.solution.service.cms.config;

import com.ftx.solution.common.base.config.Swagger2Config;
import com.ftx.solution.common.base.config.ValidationConfig;
import com.ftx.solution.common.base.handler.GlobalExceptionHandler;
import com.ftx.solution.common.base.repository.BaseRepositoryFactoryBean;
import com.ftx.solution.common.base.util.SpringContextUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 组件配置类，用于加载在common模块下面的配置
 * <p>
 * springboot默认扫描的包是启动类所在的包，因此其他的配置需要手动引入。
 * <p>
 * 配置自己的repository和要扫描的repository包路径
 *
 * @author puan
 * @date 2018-11-27 15:41
 **/
@Configuration
@Import({Swagger2Config.class,
        ValidationConfig.class,
        GlobalExceptionHandler.class,
        SpringContextUtils.class})
@EnableJpaRepositories(
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,
        basePackages = "com.ftx.solution.service.cms.dao")
@EnableTransactionManagement
public class ComponentConfig {

}
