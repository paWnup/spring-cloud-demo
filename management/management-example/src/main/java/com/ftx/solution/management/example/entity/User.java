package com.ftx.solution.management.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author puan
 * @date 2018-11-20 14:58
 **/
@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(name = "real_name")
    private String realName;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
