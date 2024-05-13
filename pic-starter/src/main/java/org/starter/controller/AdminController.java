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
import org.user.service.IAdminService;

/**
 * @description: 管理用户余额的接口 充值、消费、转账等等
 * @author: HammerRay
 * @date: 2023/12/2 下午10:56
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/recharge")
    @ApiOperation("充值接口")
    @ControllerLog(url = "/recharge", msg = "充值接口", roleType = RoleType.ADMIN)
    @ApiResponse(code = 200, message = "充值成功", response = ReBody.class)
    public ReBody recharge(@RequestBody User user) {
        return adminService.recharge(user);
    }

    @PostMapping("/makeAdmin")
    @ApiOperation("设为管理员接口")
    @ControllerLog(url = "/makeAdmin", msg = "设为管理员接口", roleType = RoleType.ADMIN)
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody makeAdmin(@RequestBody User user) {
        return adminService.makeAdmin(user.getUserId());
    }

    @PostMapping("/resetPassword")
    @ApiOperation("重置用户密码")
    @ControllerLog(url = "/resetPassword", msg = "重置用户密码", roleType = RoleType.ADMIN)
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody resetPassword(@RequestBody User user) {
        return adminService.resetPassword(user.getUserId());
    }

}
