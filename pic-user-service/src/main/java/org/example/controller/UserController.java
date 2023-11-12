package org.example.controller;

import io.swagger.annotations.*;
import org.apache.logging.log4j.Logger;
import org.example.Service.IUserService;
import org.example.log.LogComp;
import org.example.log.LogEnum;
import org.example.log.LogType;
import org.example.response.R_Code;
import org.example.response.ReBody;
import org.example.test.Add;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: eensh
 * @CreateDate: 2023/10/20
 */
@RestController
@RequestMapping("/user/api/v1")
@Api(value = "用户接口", tags = {"用户接口"})
public class UserController {
    private static final Logger log = LogComp.getLogger(UserController.class);

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    @ApiOperation("hello world")
    public String get() {
        return Add.add();
    }

    @GetMapping("/test")
    @ApiOperation("测试日志输出")
    public String testLog() {
        LogComp.LogMessage logData = LogComp.buildData(LogType.TEST);
        logData.build(LogEnum.TEST_OK);
        log.info(logData.log());
        return logData.log();
    }


    @GetMapping("/testContent")
    @ApiOperation("测试系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "1:ok 2:fail 3:error other:paramError", required = true, paramType = "form", dataType = "Integer")
    })
    public ReBody testContent(@RequestParam(name = "code") int code) {
        switch (code) {
            case 1:
                return new ReBody(R_Code.R_Ok);
            case 2:
                return new ReBody(R_Code.R_Fail);
            case 3:
                return new ReBody(R_Code.R_Error);
            default:
                return new ReBody(R_Code.R_ParamError);
        }
    }

}
