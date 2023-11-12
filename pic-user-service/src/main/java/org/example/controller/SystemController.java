package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.Logger;
import org.example.Service.ISystemService;
import org.example.common.verify.MathVerifyCodeComp;
import org.example.domain.MathVerityBody;
import org.example.log.LogComp;
import org.example.log.LogEnum;
import org.example.log.LogType;
import org.example.response.R_Code;
import org.example.response.ReBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxl17
 * @Package : org.example.controller
 * @Create on : 2023/11/12 13:50
 **/

@RestController
@RequestMapping("/system/api/v1")
@Api(value = "系统接口", tags = {"系统接口"})
public class SystemController {
    private static final Logger log = LogComp.getLogger(SystemController.class);

    private final ISystemService systemService;

    public SystemController(ISystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/math_code")
    @ApiOperation("获取算数验证码")
    public ReBody mathCode() {
        return new ReBody(R_Code.R_Ok,systemService.getMathVerityBody());
    }
}
