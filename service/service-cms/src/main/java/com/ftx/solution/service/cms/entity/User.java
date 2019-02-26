package com.ftx.solution.service.cms.entity;

import com.ftx.solution.common.base.constant.Globals;
import com.ftx.solution.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 *
 * @author puan
 * @date 2018-11-20 14:58
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Globals.DATABASE_CMS_PRE + "user")
@Data
public class User extends BaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "real_name")
    private String realName;

    @Column(length = 50, nullable = false)
    private String password;
}
