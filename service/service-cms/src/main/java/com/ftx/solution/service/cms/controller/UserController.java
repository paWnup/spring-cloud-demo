package com.ftx.solution.service.cms.controller;

import com.ftx.solution.common.base.util.ResultMap;
import com.ftx.solution.service.cms.service.UserService;
import com.ftx.solution.service.cms.vo.CreateUserVo;
import com.ftx.solution.service.cms.vo.UpdateUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author puan
 * @date 2018-11-20 15:13
 **/
@Controller
@RequestMapping("/user")
@Api(description = "用户操作接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "创建用户")
    public long createUser(@Validated CreateUserVo user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "根据ID查询用户")
    public ResultMap getUserById(@PathVariable("id") Long id) {
        return ResultMap.getSuccessResultMap(userService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    @ResponseBody
    @ApiOperation(value = "根据用户名查询用户")
    public ResultMap getUserByUsername(@PathVariable("username") String username) {
        return ResultMap.getSuccessResultMap(userService.getUserByUsername(username));
    }

    @PutMapping(value = "/")
    @ResponseBody
    @ApiOperation(value = "根据ID更新用户信息")
    public ResultMap updateUserById(@Valid UpdateUserVo user, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            return ResultMap.getFailedResultMap(list.get(0).getDefaultMessage());
        }
        userService.updateUserById(user);
        return ResultMap.getSuccessResultMap();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "根据ID删除用户")
    public ResultMap deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResultMap.getSuccessResultMap();
    }
}
