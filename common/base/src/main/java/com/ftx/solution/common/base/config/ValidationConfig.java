package com.ftx.solution.common.base.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * valid配置为快速失败返回模式
 *
 * @author puan
 * @date 2018-11-15 14:38
 **/
@Configuration
public class ValidationConfig {

    /**
     * 使用@Valid注解时
     *
     * @return Validator
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
