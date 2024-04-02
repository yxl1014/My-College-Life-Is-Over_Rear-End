package org.test.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @Package : org.test.controller
 * @Create on : 2024/4/2 下午1:42
 **/

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get")
    public String get() {
        System.out.println("test1");
        return "hello world!";
    }

    @PostMapping("/post")
    public String post(@RequestParam(name = "username") String username) {
        System.out.println("test2 - username: " + username);
        return "获取到表单数据" + username + "hello world!";
    }

    @PostMapping("/postJson")
    public String postJson(@RequestBody ExecuteResult executeResult) {
        return "获取到表单数据" + executeResult.success + "   " + executeResult.message + "hello world!";
    }

    private static class ExecuteResult {
        boolean success;
        String message;

        public ExecuteResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public ExecuteResult() {
        }
    }
}
