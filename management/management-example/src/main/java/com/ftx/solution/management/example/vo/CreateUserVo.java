package com.ftx.solution.management.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户值对象
 *
 * @author puan
 * @date 2018-11-20 15:15
 **/
@Data
@ApiModel
public class CreateUserVo {

    @ApiModelProperty(name = "用户名", dataType = "string", required = true)
    @NotNull(message = "用户名不允许为空")
    private String username;

    @ApiModelProperty(name = "密码", dataType = "string", required = true)
    @Length(max = 8, min = 6, message = "密码长度应为6到8位")
    @Pattern(regexp = "[a-zA-Z0-9_]{6,8}", message = "密码应为6到8位数字字母下划线组合")
    private String password;

}
