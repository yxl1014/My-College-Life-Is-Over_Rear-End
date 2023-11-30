package org.example.controller;

import org.example.model.dao.RoleMapper;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.service.impl.RoleMapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/28
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleMapperImpl roleMapperImpl;

    public RoleController(RoleMapperImpl roleMapperImpl) {
        this.roleMapperImpl = roleMapperImpl;
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable("roleId") Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        Role result = roleMapperImpl.selectOneRole(role);
        System.out.println(result);
        if (result != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{roleId}/powers/{powerId}")
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

    @GetMapping("/users/{userId}/roles/{roleId}")
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
}
