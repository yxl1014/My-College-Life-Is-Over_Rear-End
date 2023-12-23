package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.user.entity.response.DigitOperaCodeResponse;
import org.user.entity.response.StringCodeResponse;
import org.user.service.validationcode.VcsEmailService;
import org.user.service.validationcode.VcsPictureService;
import org.user.service.validationcode.VcsTelephoneService;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @description: 三种验证码的获取验证
 * @author: HammerRay
 * @date: 2023/11/26 上午7:46
 */
@RestController
@Api(tags =  "获取验证码接口")
@RequestMapping("valida_code")
public class ValidationCodeController {
    @Autowired
    VcsPictureService vcsPictureService;
    @Autowired
    VcsTelephoneService vcsTelephoneService;
    @Autowired
    VcsEmailService vcsEmailService;
    /**
     * description:
     * @paramType []
     * @returnType: java.lang.String
     * @author: GodHammer
     * @date: 2023-11-23 下午9:59
     */
    @GetMapping("get_picture")
    @ApiOperation("获取图形验证码")
    @ApiResponse(code = 200,message = "获取图形验证码成功",response = DigitOperaCodeResponse.class)
    public DigitOperaCodeResponse picture ()  {
        return vcsPictureService.codeSendDigitOpera();
    }

    @GetMapping("get_phone")
    @ApiOperation("获取手机验证码")
    @ApiResponse(code = 200,message = "获取手机验证码成功" , response = StringCodeResponse.class)
    public StringCodeResponse phone (@ApiParam("电话号码") @RequestParam("phoneNum") String phoneNum)  {
        return vcsTelephoneService.codeSend(phoneNum);
    }

    @GetMapping("get_email")
    @ApiOperation("获取邮箱验证码")
    @ApiResponse(code = 200,message = "获取邮箱验证码成功" , response = StringCodeResponse.class)
    public StringCodeResponse email (@ApiParam("邮箱地址") @RequestParam("emailAddr") String emailAddr) throws MessagingException, IOException {
        return vcsEmailService.codeSend(emailAddr);
    }
}
