package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commons.annotation.ControllerLog;
import org.commons.annotation.NeedCheck;
import org.commons.domain.RoleType;
import org.commons.response.ReBody;
import org.database.mysql.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.user.entity.request.VerityRequest;
import org.user.service.IUserService;


/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ControllerLog(url = "/login",msg = "登录",roleType = RoleType.PROVIDER)
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = ReBody.class)
    public ReBody login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/login_tel")
    @ControllerLog(url = "/login_tel",msg = "登录-电话",roleType = RoleType.PROVIDER)
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = ReBody.class)
    public ReBody loginTel(@RequestBody VerityRequest request) {
        return userService.loginVerity(request, 1);
    }

    @PostMapping("/login_email")
    @ControllerLog(url = "/login_email",msg = "登录-邮箱",roleType = RoleType.PROVIDER)
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = ReBody.class)
    public ReBody loginEmail(@RequestBody VerityRequest request) {
        return userService.loginVerity(request, 2);
    }

    @GetMapping("/logout")
    @ControllerLog(url = "/logout",msg = "登出",roleType = RoleType.PROVIDER)
    @NeedCheck
    @ApiOperation("登出---redis中删除相关UUID")
    @ApiResponse(code = 200, message = "登出成功", response = ReBody.class)
    public ReBody logout() {
        return userService.logOff();
    }

    @PostMapping("/register")
    @ControllerLog(url = "/register",msg = "注册",roleType = RoleType.PROVIDER)
    @ApiOperation("注册 返回注册好的账户的uuid，可做注册后自动登录")
    @ApiResponse(code = 200, message = "注册成功", response = ReBody.class)
    public ReBody register(@RequestBody User user) {
        return userService.register(user);
    }


    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    @NeedCheck
    @ControllerLog(url = "/userInfo",msg = "获取用户信息",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "", response = ReBody.class)
    public ReBody userInfo() {
        return userService.userInfo();
    }

    @PostMapping("/upRole")
    @ControllerLog(url = "/upRole",msg = "成为任务发布者",roleType = RoleType.PROVIDER)
    @ApiOperation("成为任务发布者")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody upRole(@RequestBody User user) {
        return userService.upRole(user);
    }


    @PostMapping("/updateUserInfo")
    @ControllerLog(url = "/updateUserInfo",msg = "修改用户信息",roleType = RoleType.PROVIDER)
    @ApiOperation("修改用户信息")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }
}
