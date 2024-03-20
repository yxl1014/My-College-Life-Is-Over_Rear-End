package org.starter.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commons.annotation.ControllerLog;
import org.commons.domain.RoleType;
import org.commons.response.ReBody;
import org.database.mysql.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.user.entity.response.transaction.RechargeResponse;
import org.user.service.transaction.RechargeService;

/**
 * @description: 管理用户余额的接口 充值、消费、转账等等
 * @author: HammerRay
 * @date: 2023/12/2 下午10:56
 */
@RestController
@RequestMapping("/trans")
@Api(tags = "用户余额管理接口")
public class RestMoneyManageController {
    @Autowired
    RechargeService rechargeService;

    @PostMapping("/recharge")
    @ApiOperation("充值接口")
    @ControllerLog(url = "/findUpdatePwdRequest",msg = "验证并修改密码",roleType = RoleType.ADMIN)
    @ApiResponse(code = 200,message = "充值成功",response = RechargeResponse.class)
    public ReBody recharge (@RequestBody User user){
        return rechargeService.recharge(user);
    }

}
