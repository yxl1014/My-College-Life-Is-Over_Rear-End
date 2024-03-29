package org.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/3
 */


@SpringBootConfiguration
@EnableCaching
@SpringBootApplication(scanBasePackages = {"org.database", "org.user", "org.starter", "org.task"},
        // 这两个排除掉 不然会有一个spring 自带的身份验证 需要启动生成的一个code 用户名是user
        exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class);
    }
}
