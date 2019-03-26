package com.ftx.solution.common.base.util;


import com.ftx.solution.common.base.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 实体验证工具类
 *
 * @author su
 * @date 2018-11-07
 */
public class ValidatorUtils {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 验证并且在不通过时抛出异常消息
     *
     * @param t
     * @param groups
     * @param <T>
     */
    public static <T> void validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        String message = generalMethod(validateResult);
        if (!StringUtils.isEmpty(message)) {
            throw new BusinessException(message);
        }
    }

    /**
     * Collection 验证并且在不通过时抛出异常消息
     *
     * @param collection
     * @param groups
     * @param <T>
     */
    public static <T> void validateCollection(Collection<T> collection, Class... groups) {
        StringBuilder message = new StringBuilder();
        Validator validator = validatorFactory.getValidator();
        collection.forEach(e -> {
            Set<ConstraintViolation<T>> validateResult = validator.validate(e, groups);
            if (!validateResult.isEmpty()) {
                message.append(generalMethod(validateResult));
            }
        });
        if (!StringUtils.isEmpty(message.toString())) {
            throw new BusinessException(message.toString());
        }
    }

    /**
     * 通用validateResult处理方法
     *
     * @param validateResult
     * @param <T>
     * @return
     */
    private static <T> String generalMethod(Set<ConstraintViolation<T>> validateResult) {
        if (!validateResult.isEmpty()) {
            if (!CollectionUtils.isEmpty(validateResult)) {
                Iterator<ConstraintViolation<T>> t = validateResult.iterator();
                return t.next().getMessage();
            }
        }
        return null;
    }

    /**
     * 获取验证结果set
     *
     * @param t
     * @param groups
     * @param <T>
     */
    public static <T> Set<ConstraintViolation<T>> getValidateResult(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        return validator.validate(t, groups);
    }

    /**
     * 获取 Validator
     *
     * @param <T>
     */
    public static <T> Validator getValidator() {
        return validatorFactory.getValidator();
    }

}
