package org.example.controller;

import io.swagger.annotations.ApiOperation;
import org.example.model.dao.PowerMapper;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.service.impl.PowerMapperImpl;
import org.example.service.impl.RoleMapperImpl;
import org.example.service.impl.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@RestController
@RequestMapping("/Limits")
public class LimitsController {
    private final UserMapperImpl userMapperImpl;
    private final RoleMapperImpl roleMapperImpl;
    private final PowerMapperImpl powerMapperImpl;

    @Autowired
    public LimitsController(UserMapperImpl userMapperImpl, RoleMapperImpl roleMapperImpl, PowerMapperImpl powerMapperImpl) {
        this.userMapperImpl = userMapperImpl;
        this.roleMapperImpl = roleMapperImpl;
        this.powerMapperImpl = powerMapperImpl;
    }

    //测试sheen
    @GetMapping("/sheen")
    @ApiOperation("测试联通")
    public String get() {
        return "hello sheen!";
    }

    //测试查询用户列表信息
    @GetMapping("/users")
    public ResponseEntity<?> getUser() {
        List<User> users = userMapperImpl.selectAllUser();
        return ResponseEntity.ok(users);
    }

    //测试查询角色列表信息
    @GetMapping("/roles")
    public ResponseEntity<?> getRole() {
        List<Role> roles = roleMapperImpl.selectAllRole();
        return ResponseEntity.ok((roles));
    }

    //测试查询权限列表信息
    @GetMapping("/powers")
    public ResponseEntity<?> getPower() {
        List<Power> powers = powerMapperImpl.selectAllPower();
        return ResponseEntity.ok((powers));
    }

    //测试查询用户
    @GetMapping("/selectOneUser/{userEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("userEmail") String userSysEmail) {
        User user = new User();
        user.setUserSysEmail(userSysEmail);
        User user1 = userMapperImpl.selectOneUser(user);
        if (user1 != null) {
            return ResponseEntity.ok(user1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试查询角色
    @GetMapping("/selectOneRole/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable("roleId") Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        Role role1 = roleMapperImpl.selectOneRole(role);
        if (role1 != null) {
            return ResponseEntity.ok(role1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试查询权限
    @GetMapping("/selectOnePower/{powerId}")
    public ResponseEntity<Power> getPowerById(@PathVariable("powerId") Integer powerId) {
        Power power = new Power();
        power.setPowerId(powerId);
        Power power1 = powerMapperImpl.selectOnePower(power);
        if (power1 != null) {
            return ResponseEntity.ok(power1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试角色赋予权限
    @GetMapping("/testGrantPowerToRole/roles/{roleId}/powers/{powerId}")
    public ResponseEntity<String> getPowerToRole(@PathVariable("roleId") Integer roleId, @PathVariable("powerId") Integer powerId) {
        Role role = new Role();
        Power power = new Power();
        role.setRoleId(roleId);
        power.setPowerId(powerId);
        //执行授权操作
        roleMapperImpl.grantPowerToRole(roleId, powerId);
        // 验证是否成功
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        if (isGranted) {
            return ResponseEntity.ok("权限已成功授予角色");
        } else {
            return ResponseEntity.badRequest().body("授权失败");
        }

    }

    //测试用户赋予角色
    @GetMapping("/testGrantUserToRole/users/{userId}/roles/{roleId}")
    public ResponseEntity<String> getUserToRole(@PathVariable("userId") String userId, @PathVariable("roleId") Integer roleId) {
        Role role = new Role();
        User user = new User();
        role.setRoleId(roleId);
        user.setUserId(userId);
        //执行授权操作
        roleMapperImpl.grantUserToRole(userId, roleId);
        // 验证是否成功
        boolean isGranted = roleMapperImpl.isUserGrantedToRole(userId, roleId);
        if (isGranted) {
            return ResponseEntity.ok("用户已成功授予角色");
        } else {
            return ResponseEntity.badRequest().body("授权失败");
        }

    }

    //测试角色回收已有权限
    @GetMapping("/revokePowerFromRole/roleId/{roleId}/powers/{powerId}")
    public ResponseEntity<String> checkPowerRevoked(@PathVariable("roleId") Integer roleId, @PathVariable("powerId") Integer powerId) {
        // 验证权限是否已成功撤销
        Role role = new Role();
        Power power = new Power();
        role.setRoleId(roleId);
        power.setPowerId(powerId);
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        if (isGranted) {
            // 执行撤销权限操作
            roleMapperImpl.revokePowerFromRole(roleId, powerId);
            // 验证是否成功撤销
            boolean isRevoked = !roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
            if (isRevoked) {
                return ResponseEntity.ok("权限已成功撤销");
            } else {
                return ResponseEntity.ok("权限未撤销");
            }
        } else {
            return ResponseEntity.ok("该角色无此权限，无需删除！");
        }
    }

    //获取角色拥有的权限列表
    @GetMapping("/getRolePowers/{roleId}/powers")
    public ResponseEntity<List<Power>> getRolePowers(@PathVariable("roleId") Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        List<Power> powers = powerMapperImpl.getRolePowers(roleId);
        if (powers != null && !powers.isEmpty()) {
            return ResponseEntity.ok(powers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



