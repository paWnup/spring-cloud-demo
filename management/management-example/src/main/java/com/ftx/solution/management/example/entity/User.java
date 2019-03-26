package com.ftx.solution.management.example.entity;

import com.ftx.solution.common.base.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * @author puan
 * @date 2018-11-20 14:58
 **/
@Entity
@Table(name = "user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String username;

    @Column(name = "real_name")
    private String realName;

    @Column(length = 50, nullable = false)
    private String password;
}
