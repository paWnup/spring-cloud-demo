package com.ftx.solution.common.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puan
 * @date 2018-11-15 15:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 错误信息
     */
    private String message;

}
