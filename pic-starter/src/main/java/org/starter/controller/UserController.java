package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.commons.response.ReBody;
import org.database.mysql.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     * $$考虑添加LoginRequest实体类，来对应前端传来的jsonBody中的各个属性  同理RegisterRequest
     *
     * @param user:
     * @paramType: [java.lang.Object]
     * @returnType: org.springframework.web.servlet.ModelAndView
     * @author: GodHammer
     * @date: 2023-11-26 下午9:07
     * @version: v1.0
     */
    @PostMapping("/login")
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = ReBody.class)
    public ReBody login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/login_tel")
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = ReBody.class)
    public ReBody loginTel(@RequestBody VerityRequest request) {
        return userService.loginVerity(request, 1);
    }

    @PostMapping("/login_email")
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = ReBody.class)
    public ReBody loginEmail(@RequestBody VerityRequest request) {
        return userService.loginVerity(request, 2);
    }

    @GetMapping("/logout")
    @ApiOperation("登出---redis中删除相关UUID")
    @ApiResponse(code = 200, message = "登出成功", response = ReBody.class)
    public ReBody logout(@RequestParam @ApiParam("用户的uuid") String uuid) {
        return userService.logOff(uuid);
    }

    @PostMapping("/register")
    @ApiOperation("注册 返回注册好的账户的uuid，可做注册后自动登录")
    @ApiResponse(code = 200, message = "注册成功", response = ReBody.class)
    public ReBody register(@RequestBody User user) {
        return userService.register(user);
    }
}
