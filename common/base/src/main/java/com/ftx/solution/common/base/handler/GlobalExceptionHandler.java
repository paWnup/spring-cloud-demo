package com.ftx.solution.common.base.handler;

import com.ftx.solution.common.base.exception.BusinessException;
import com.ftx.solution.common.base.util.ResultMap;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理配置类
 *
 * @author puan
 * @date 2018-11-15 15:34
 **/
@ControllerAdvice
@Log4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * @param exception 业务异常
     * @return 操作失败提示
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultMap businessExceptionHandler(BusinessException exception) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return ResultMap.getFailedResultMap(exception.getMessage());
    }

    /**
     * 默认异常处理
     * @param exception 默认异常
     * @return 服务器错误提示
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMap exceptionHandler(Exception exception) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return ResultMap.getFailedResultMap("服务器错误！");
    }
}
