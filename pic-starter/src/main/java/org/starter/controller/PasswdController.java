package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.commons.response.ReBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.user.entity.response.SecProblemResponse;
import org.user.service.FindPasswordService;


/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
@Api(tags = "密码操作接口")
@RequestMapping("/passwd")
public class PasswdController {

    private final FindPasswordService findPasswordService;

    public PasswdController(FindPasswordService findPasswordService) {
        this.findPasswordService = findPasswordService;
    }

    //1、请求一下数字验证码的接口
    //2、验证用户名是否存在 （用户名 UUID 验证码）
    //返回值：uuid（第一步uuid和第二步绑定的uuid）、userID、密保问题、电话、邮箱
    //（可能存在）3、请求邮箱或手机验证码（uuid、type=1手机、=2邮箱）
    //返回值：uuid（验证码的）
    //慢慢来👻:
    //4.1验证密保（uuid，答案）
    //4.2验证手机或邮箱（uuid（修改密码步骤内的），uuid（验证码的），验证码）
    //返回值 需要生成一个新的uuid
    //5、uuid、新密码

    @GetMapping("/checkExist")
    @ApiOperation("检查用户是否存在")
    @ApiResponse(code = 200, message = "请求成功", response = SecProblemResponse.class)
    public ReBody checkExist(@ApiParam("用户名") @RequestParam("string")String string) {
        return findPasswordService.checkExist(string);
    }
}
