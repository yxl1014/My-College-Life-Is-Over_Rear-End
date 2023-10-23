package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Service.IUserService;
import org.example.comp.UserRoleComp;
import org.example.test.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/10/20
 */
@RestController
@RequestMapping("/user/api/v1")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public String get(){
        return Add.add();
    }

}
