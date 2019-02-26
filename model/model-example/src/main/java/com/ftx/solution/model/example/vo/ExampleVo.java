package com.ftx.solution.model.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 1 在management模块和service模块引入，用于management及service之间相互传值。
 * 2 用于swaggerAPI编写
 * 3 接收请求参数，并进行参数验证
 *
 * @author puan
 * @date 2018-11-15 10:31
 **/
@ApiModel
@Data
public class ExampleVo {

    @ApiModelProperty(value = "ID", required = false, dataType = "int")
    private int id;

    @ApiModelProperty(value = "姓名", required = true, dataType = "String")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "年龄", required = true, dataType = "int")
    @NotNull(message = "年龄不能为空")
    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private Integer age;

    @ApiModelProperty(value = "密码", required = true, dataType = "String")
    @Length(max = 8, min = 6, message = "密码长度应为6到8位")
    @Pattern(regexp = "[a-zA-Z0-9_]{6,8}", message = "密码应为6到8位数字字母下划线组合")
    private String password;

}
