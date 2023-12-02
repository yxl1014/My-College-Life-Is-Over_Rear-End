package example.controller;

import example.entity.request.transaction.RechargeRequest;
import example.entity.response.transaction.RechargeResponse;
import example.service.transaction.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 管理用户余额的接口 充值、消费、转账等等
 * @author: HammerRay
 * @date: 2023/12/2 下午10:56
 */
@RestController
@RequestMapping("/trans")
@Api("用户余额管理接口")
public class RestMoneyManageController {
    @Autowired
    RechargeService rechargeService;

    @PostMapping("/recharge")
    @ApiOperation("充值接口")
    @ApiResponse(code = 200,message = "充值成功",response = RechargeResponse.class)
    public RechargeResponse recharge (RechargeRequest request){


        return rechargeService.recharge(request);
    }

}
