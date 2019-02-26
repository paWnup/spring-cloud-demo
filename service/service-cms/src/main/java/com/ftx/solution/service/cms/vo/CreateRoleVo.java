package com.ftx.solution.service.cms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 角色值对象
 *
 * @author puan
 * @date 2018-11-20 15:15
 **/
@Data
@ApiModel
public class CreateRoleVo {

    @ApiModelProperty(name = "角色名称", dataType = "string", required = true)
    @NotNull(message = "角色不允许为空")
    private String roleName;

    @ApiModelProperty(name = "角色编码", dataType = "string", required = true)
    @Pattern(regexp = "[a-z_A-Z]", message = "角色编码只能字母和下划线组成")
    private String roleCode;

}
