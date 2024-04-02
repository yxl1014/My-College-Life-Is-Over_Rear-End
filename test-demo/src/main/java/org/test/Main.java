package org.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Administrator
 * @Package : org.test
 * @Create on : 2024/4/2 下午1:38
 **/


@SpringBootConfiguration
@EnableCaching
@SpringBootApplication(
        // 这两个排除掉 不然会有一个spring 自带的身份验证 需要启动生成的一个code 用户名是user
        exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
            SpringApplication.run(Main.class);
    }
}