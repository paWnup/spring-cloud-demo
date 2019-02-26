package com.ftx.solution.management.example.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author puan
 * @date 2018-11-20 15:31
 **/
@Data
public class GetUserVo {

    private Long id;

    private String username;

    private String realName;

    private String password;

    private Date createTime;

    private Date updateTime;
}
