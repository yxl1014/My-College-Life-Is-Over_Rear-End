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
import java.util.Map;

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
    @GetMapping("/users/All")
    public ResponseEntity<?> getUser() {
        List<User> users = userMapperImpl.selectAllUser();
        return ResponseEntity.ok(users);
    }

    //测试由用户id 或者用户名 或者用户邮箱 或者用户电话 查询用户列表信息
    @GetMapping("/users/userInfo/{userInfo}")
    public ResponseEntity<User> selectOneUser(@PathVariable("userInfo") String query) {
        User user = userMapperImpl.selectOneUser(query);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试查询权限列表信息
    @GetMapping("/powers/All")
    public ResponseEntity<?> getPower() {
        List<Power> powers = powerMapperImpl.selectAllPower();
        return ResponseEntity.ok((powers));
    }

    //测试由权限id查到权限
    @GetMapping("/powers/powerId/{powerId}")
    public ResponseEntity<Power> selectOnePower(@PathVariable("powerId") Integer powerId) {
        Power power = powerMapperImpl.selectOnePower(powerId);
        if (power != null) {
            return ResponseEntity.ok(power);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试角色对应的权限列表
    @GetMapping("/powers/getRolePowers/{roleId}")
    public ResponseEntity<?> getRolePowers(@PathVariable("roleId") Integer roleId) {
        List<Power> powers = powerMapperImpl.getRolePowers(roleId);
        if (powers != null && !powers.isEmpty()) {
            return ResponseEntity.ok(powers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // //判断权限的状态为可操作还是可访问(1为可操作，2为可访问)
    @GetMapping("/powers/isStatusToPower/{powerId}")
    public ResponseEntity<?> isStatusToPower(@PathVariable("powerId") Integer powerId) {
        boolean isStatusToPower = powerMapperImpl.isStatusToPower(powerId);
        if (isStatusToPower) {
            return ResponseEntity.ok("可操作！");
        } else {
            return ResponseEntity.ok("可访问！");
        }
    }


    //测试查询角色列表信息
    @GetMapping("/roles/All")
    public ResponseEntity<?> getRole() {
        List<Role> roles = roleMapperImpl.selectAllRole();
        return ResponseEntity.ok((roles));
    }

    //测试由角色id或角色名称查询角色
    @GetMapping("/roles/selectOneRole/roleInfo/{roleInfo}")
    public ResponseEntity<Role> selectOneRole(@PathVariable("roleInfo") String query) {
        Role role = roleMapperImpl.selectOneRole(query);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试由角色id查询角色
    @GetMapping("/roles/findRoleById/{roleId}")
    public ResponseEntity<Role> findRoleById(@PathVariable("roleId") Integer roleId) {
        Role role = roleMapperImpl.findRoleById(roleId);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //测试由角色名称查询角色
    @GetMapping("/roles/findRoleByRoleName/{roleName}")
    public ResponseEntity<Role> findRoleByRoleName(@PathVariable("roleName") String roleName) {
        Role role = roleMapperImpl.findRoleByRoleName(roleName);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //不同角色赋予不同权限（可访问or可操作）
    @GetMapping("/roles/grantPowerToRole/roles/{roleId}/powers/{powerName}/{powerType}")
    public ResponseEntity<String> getPowerToRole(@PathVariable("roleId") Integer roleId, @PathVariable("powerName") String powerName, @PathVariable("powerType") int powerType) {
        boolean op = roleMapperImpl.grantPowerToRole(roleId, powerName, powerType);
        if (op) {
            return ResponseEntity.ok("授权成功！");
        } else {
            return ResponseEntity.ok("授权失败！");
        }
    }

    //分配角色给用户
    @GetMapping("/roles/grantUserToRole/userId/{userId}/roleId/{roleId}")
    public ResponseEntity<String> grantUserToRole(@PathVariable("userId") String userId, @PathVariable("roleId") Integer roleId) {
        boolean op = roleMapperImpl.grantUserToRole(userId, roleId);
        if (op) {
            return ResponseEntity.ok("分配成功！");
        } else {
            return ResponseEntity.ok("分配失败！");
        }
    }

    //撤销已分配给角色的权限
    @GetMapping("/roles/revokePowerFromRole/roleId/{roleId}/powerId/{powerId}")
    public ResponseEntity<String> revokePowerFromRole(@PathVariable("roleId") Integer roleId, @PathVariable("powerId") Integer powerId) {
        boolean op = roleMapperImpl.revokePowerFromRole(roleId, powerId);
        if (op) {
            return ResponseEntity.ok("撤销已分配给角色的权限成功！");
        } else {
            return ResponseEntity.ok("撤销已分配给角色的权限失败！");
        }
    }

    //撤销已分配给用户的角色
    @GetMapping("/roles/revokeUserFromRole/userId/{userId}/roleId/{roleId}")
    public ResponseEntity<String> revokeUserFromRole(@PathVariable("userId") String userId, @PathVariable("roleId") Integer roleId) {
        boolean op = roleMapperImpl.revokeUserFromRole(userId, roleId);
        if (op) {
            return ResponseEntity.ok("撤销已分配给用户的角色成功！");
        } else {
            return ResponseEntity.ok("撤销已分配给用户的角色失败！");
        }
    }

    //查询可用状态下角色
    @GetMapping("/role/isAbleToRole")
    public ResponseEntity<?> isAbleToRole() {
        int roleFlag = 0;
        List<Role> roleList = roleMapperImpl.isAbleToRole(roleFlag);
        return ResponseEntity.ok(roleList);
    }

    //判断用户的角色
    @GetMapping("/roles/isUserWhatToRole/userId/{userId}")
    public ResponseEntity<String> isUserWhatToRole(@PathVariable("userId") String userId) {
        String roleId = roleMapperImpl.isUserWhatToRole(userId);
        if (roleId != null) {
            return ResponseEntity.ok(roleId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //登陆
    @PostMapping("/user/login")
    public String login(@RequestBody Map<String ,String> credentials){
        String userName=credentials.get("userName");
        String userPassword=credentials.get("userPassword");
        if (userMapperImpl.loginUser(userName,userPassword)){
            return "登陆成功";
        }else {
            return "用户名或密码错误";
        }
    }





}

