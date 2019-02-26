package com.ftx.solution.common.rabbitmq.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 给消息加一个ID
 *
 * @author puan
 * @date 2018-11-26 16:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageWithId {

    private String id;

    private String message;
}
