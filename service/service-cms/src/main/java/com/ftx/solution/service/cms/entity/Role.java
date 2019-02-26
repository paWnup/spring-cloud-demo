package com.ftx.solution.service.cms.entity;

import com.ftx.solution.common.base.constant.Globals;
import com.ftx.solution.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色
 *
 * @author puan
 * @date 2018-12-06 11:36
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Globals.DATABASE_CMS_PRE + "role")
@Data
public class Role extends BaseEntity {

    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    @Column(name = "role_code", length = 10, nullable = false, unique = true)
    private String roleCode;

}
