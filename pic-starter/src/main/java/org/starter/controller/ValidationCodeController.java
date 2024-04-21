package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.commons.annotation.ControllerLog;
import org.commons.domain.RoleType;
import org.commons.response.ReBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.user.entity.request.VerityRequest;
import org.user.service.IValidationCodeService;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @description: 三种验证码的获取验证
 * @author: HammerRay
 * @date: 2023/11/26 上午7:46
 */
@RestController
@Api(tags = "获取验证码接口")
@RequestMapping("/valida_code")
public class ValidationCodeController {

    @Autowired
    private IValidationCodeService validationCodeService;

    /**
     * description:
     *
     * @paramType []
     * @returnType: java.lang.String
     * @author: GodHammer
     * @date: 2023-11-23 下午9:59
     */
    @GetMapping("/get_picture")
    @ApiOperation("获取图形验证码")
    @ControllerLog(url = "/get_picture",msg = "获取图形验证码",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "获取图形验证码成功", response = ReBody.class)
    public ReBody picture() {
        return validationCodeService.genMathVerityCode();
    }

    @GetMapping("/get_phone")
    @ApiOperation("获取手机验证码")
    @ControllerLog(url = "/get_phone",msg = "获取手机验证码",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "获取手机验证码成功", response = ReBody.class)
    public ReBody phone(@ApiParam("电话号码") @RequestParam("phoneNum") String phoneNum) {
        return validationCodeService.genTelVerityCode(phoneNum);
    }

    @GetMapping("/get_email")
    @ApiOperation("获取邮箱验证码")
    @ControllerLog(url = "/get_email",msg = "获取邮箱验证码",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "获取邮箱验证码成功", response = ReBody.class)
    public ReBody email(@ApiParam("邮箱地址") @RequestParam("emailAddr") String emailAddr) throws MessagingException, IOException {
        return validationCodeService.genEmailVerityCode(emailAddr);
    }


    @PostMapping("/verity_picture_code")
    @ApiOperation("验证图形验证码")
    @ControllerLog(url = "/verity_picture_code",msg = "验证图形验证码",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "验证图形验证码成功", response = ReBody.class)
    public ReBody verityPictureCode(@ApiParam("验证码UUID+验证码") @RequestBody VerityRequest verityRequest) {
        return validationCodeService.verityCode(verityRequest, 1);
    }

    @PostMapping("/verity_tel_code")
    @ApiOperation("验证电话验证码")
    @ControllerLog(url = "/verity_tel_code",msg = "验证电话验证码",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "验证电话验证码成功", response = ReBody.class)
    public ReBody verityTelCode(@ApiParam("验证码UUID+验证码") @RequestBody VerityRequest verityRequest) {
        return validationCodeService.verityCode(verityRequest, 2);
    }

    @PostMapping("/verity_email_code")
    @ApiOperation("验证邮箱验证码")
    @ControllerLog(url = "/verity_email_code",msg = "验证邮箱验证码",roleType = RoleType.PROVIDER)
    @ApiResponse(code = 200, message = "验证邮箱验证码成功", response = ReBody.class)
    public ReBody verityEmailCode(@ApiParam("验证码UUID+验证码") @RequestBody VerityRequest verityRequest) {
        return validationCodeService.verityCode(verityRequest, 3);
    }
}
