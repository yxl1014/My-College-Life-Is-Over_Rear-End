package org.example.controller;

import org.example.test.Add;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: eensh
 * @CreateDate: 2023/10/20
 */
@RestController
@RequestMapping("/user/api/v1")
public class UserController {
    @GetMapping("/get")
    public String get(){return Add.add();}
}
