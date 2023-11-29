package org.example.controller;

import org.example.model.dao.RoleMapper;
import org.example.service.impl.RoleMapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/grantPowerToRole")
    public ResponseEntity<String> grantPowerToRole(
            @RequestParam("roleId") Integer roleId,
            @RequestParam("powerId") Integer powerId) {

        //执行授权操作
        roleMapperImpl.grantPowerToRole(roleId,powerId);
        // 验证是否成功
        boolean isGranted=roleMapperImpl.isPowerGrantedToRole(roleId,powerId);
        if(isGranted){
            return ResponseEntity.ok("权限已成功授予角色");
        }else {
            return ResponseEntity.badRequest().body("授权失败");
        }
    }

    @PostMapping("/grantUserToRole")
    public ResponseEntity<String> grantUserToRole(
            @RequestParam("userId") String  userId,
            @RequestParam("roleId") Integer roleId) {

        //执行授权操作
        roleMapperImpl.grantUserToRole(userId,roleId);
        // 验证是否成功
        boolean isGranted=roleMapperImpl.isUserGrantedToRole(userId,roleId);
        if(isGranted){
            return ResponseEntity.ok("用户已成功授予角色");
        }else {
            return ResponseEntity.badRequest().body("授权失败");
        }
    }

}
