package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commons.response.ReBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.user.entity.request.RegisterRequest;
import org.user.entity.response.UuidResponse;
import org.user.service.RegisterService;

import java.text.ParseException;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
@Api(tags =  "注册接口")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    @ApiOperation("注册 返回注册好的账户的uuid，可做注册后自动登录")
    @ApiResponse(code = 200,message = "注册成功",response = UuidResponse.class)
    public ReBody register(@RequestBody RegisterRequest request) throws  ParseException {


        return registerService.register(request);
    }

}
