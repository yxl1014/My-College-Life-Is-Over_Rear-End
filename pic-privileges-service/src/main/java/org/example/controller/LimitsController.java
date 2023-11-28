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

    @GetMapping("/users")
    public ResponseEntity<?> getUser() {
        List<User> users = userMapperImpl.selectAllUser();
        return ResponseEntity.ok(users);
    }
    
}


