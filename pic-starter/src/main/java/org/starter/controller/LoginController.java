package org.starter.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.commons.response.ReBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.user.entity.request.LoginRequest;
import org.user.entity.response.UuidResponse;
import org.user.service.LoginService;
import org.user.service.LogoffService;

import java.io.IOException;


/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
@Api(tags = "登录接口")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    LogoffService logoffService;
    /**
     * $$考虑添加LoginRequest实体类，来对应前端传来的jsonBody中的各个属性  同理RegisterRequest
     *
     * @param loginRequest:
     * @paramType: [java.lang.Object]
     * @returnType: org.springframework.web.servlet.ModelAndView
     * @author: GodHammer
     * @date: 2023-11-26 下午9:07
     * @version: v1.0
     */
    @PostMapping("/login")
    @ApiOperation("登录---通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ApiResponse(code = 200, message = "登录成功", response = UuidResponse.class)
    public ReBody login(@RequestBody LoginRequest loginRequest) throws IOException {
        return loginService.login(loginRequest);
    }

    @GetMapping("/logout")
    @ApiOperation("登出---redis中删除相关UUID")
    @ApiResponse(code = 200, message = "登出成功", response = UuidResponse.class)
    public ReBody logout(@RequestParam @ApiParam("用户的uuid") String uuid) throws IOException {
        return logoffService.logOff(uuid);
    }
}
