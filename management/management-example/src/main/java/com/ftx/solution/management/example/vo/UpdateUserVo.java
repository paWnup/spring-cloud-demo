package com.ftx.solution.management.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author puan
 * @date 2018-11-20 15:42
 **/
@Data
@ApiModel
public class UpdateUserVo {

    @ApiModelProperty(name = "id", dataType = "long", required = true)
    @NotNull(message = "用户不存在")
    private Long id;

    @ApiModelProperty(name = "用户名", dataType = "string", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(name = "真实姓名", dataType = "string")
    private String realName;

}
