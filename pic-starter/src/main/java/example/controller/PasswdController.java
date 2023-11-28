package example.controller;

import example.entity.request.FindPasswdRequest;
import example.entity.request.ModifyPasswdRequest;
import example.entity.response.UuidResponse;
import example.service.FindPasswordService;
import example.service.ModifyPasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/findPasswd")
    @ApiOperation("找回密码 " )
    @ResponseBody
    @ApiResponse(code = 200,message = "找回密码成功",response = UuidResponse.class)
    public UuidResponse findPasswd(@RequestBody FindPasswdRequest findPasswdRequest) {

        UuidResponse uuidResponse = findPasswordService.findPasswd(findPasswdRequest);;

        return uuidResponse;
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
