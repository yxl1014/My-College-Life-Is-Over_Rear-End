package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.commons.response.ReBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.user.entity.request.ModifyPasswdRequest;
import org.user.entity.request.findPasswd.FindPasswdRequest;
import org.user.entity.request.findPasswd.SecAnswerRequest;
import org.user.entity.response.SecProblemResponse;
import org.user.entity.response.UuidResponse;
import org.user.service.FindPasswordService;
import org.user.service.ModifyPasswordService;
import org.user.service.UserInfoQueryService;

import java.io.IOException;


/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
@Api(tags = "密码操作接口")
@RequestMapping("/passwd")
public class PasswdController {
    @Autowired
    FindPasswordService findPasswordService;
    @Autowired
    ModifyPasswordService modifyPasswordService;
    @Autowired
    UserInfoQueryService queryService;

    // 检验用户是否存在 ---> 存在true --->查询密保--->跳转到输入密保页面或输入验证码页面 -->处理正确失败 -->充值密码页面
    @GetMapping("/checkExist")
    @ApiOperation("检查用户是否存在")
    @ApiResponse(code = 200, message = "请求成功", response = SecProblemResponse.class)
    public ReBody checkExist(@ApiParam("用户名/邮箱地址/手机号") @RequestParam("string")String string){
        return findPasswordService.checkExist(string);
    }

    @GetMapping("/passwdSecQuery")
    @ApiOperation("查询密保")
    @ApiResponse(code = 200, message = "请求成功", response = SecProblemResponse.class)
    public SecProblemResponse passwdSecQuery(@ApiParam("用户名/邮箱地址/手机号") @RequestParam("string")String request) {
        return queryService.passwdSecQuery(request);
    }
    @PostMapping("/checkProperSec")
    @ApiOperation("检查密保正确性")
    public boolean checkProper(SecAnswerRequest request){
        return findPasswordService.checkProperSec(request);
    }

    @GetMapping("/checkProperVal")
    @ApiOperation("检查验证码正确性")
    public boolean checkProper(@ApiParam("验证码uuid") @RequestParam("vcId")String vcId,@ApiParam("验证码") @RequestParam("validation")String validation){
        return findPasswordService.checkProperVal(vcId,validation);
    }

    @PostMapping("/findPasswd")
    @ApiOperation("找回密码" )
    @ResponseBody
    @ApiResponse(code = 200,message = "找回密码成功",response = UuidResponse.class)
    public ReBody findPasswd(FindPasswdRequest findPasswdRequest) {

        return findPasswordService.findPasswd(findPasswdRequest);

    }

    @PostMapping("/modify_passwd")
    @ApiOperation("修改密码 ")
    @ApiResponse(code = 200,message = "修改密码成功",response = UuidResponse.class)
    public UuidResponse modifyPasswd(@RequestBody ModifyPasswdRequest modifyPasswdRequest) throws IOException {
        UuidResponse uuidResponse = new UuidResponse();
        modifyPasswordService.modifyPasswd(modifyPasswdRequest);
        return uuidResponse;
    }
}
