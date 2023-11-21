package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/16
 */
@RestController
public class TestController {

    @GetMapping("/sheen")
    public String Hello(){
        return "第一个接口";
    }
}

