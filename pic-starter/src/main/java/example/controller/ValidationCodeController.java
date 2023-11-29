package example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.service.validation_code.VcsEmailService;
import example.service.validation_code.VcsPictureService;
import example.service.validation_code.VcsTelephoneService;
import example.service.validation_code.entity.DigitalOperationCode;
import example.service.validation_code.entity.StringCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @ApiResponse(code = 200,message = "获取图形验证码成功",response = DigitalOperationCode.class)
    public DigitalOperationCode picture ()  {

        return vcsPictureService.codeSendDigitOpera();

    }

    @PostMapping("get_phone")
    @ApiOperation("获取手机验证码")
    @ApiResponse(code = 200,message = "获取手机验证码成功" , response = StringCode.class)
    public StringCode phone (@RequestBody @ApiParam("电话号码") String phoneNum)  {

        return vcsTelephoneService.codeSend(phoneNum);
    }

    @PostMapping("get_email")
    @ApiOperation("获取邮箱验证码")
    @ApiResponse(code = 200,message = "获取邮箱验证码成功" , response = StringCode.class)
    public StringCode email (@RequestBody @ApiParam("邮箱地址") String emailAddr) throws MessagingException, IOException {


        return vcsEmailService.codeSend(emailAddr);

    }
}
