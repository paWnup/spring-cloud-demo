package com.ftx.solution.common.base.controller;

import com.ftx.solution.common.base.util.ResultMap;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author puan
 * @date 2019-03-26 10:04
 **/
@Component
public class ControllerAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        return methodParameter.hasMethodAnnotation(ResponseBody.class);
    }

    /**
     * 再返回前对结果进行处理
     *
     * @param result          返回的数据
     * @param methodParameter 方法声明的数据
     * @param mediaType       content-type
     * @param clazz           类
     * @param request         请求
     * @param response        响应
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType,
                                  Class clazz, ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        if (result instanceof ResultMap) {
            return result;
        }
        return ResultMap.getSuccessResultMap(result);
    }
}
