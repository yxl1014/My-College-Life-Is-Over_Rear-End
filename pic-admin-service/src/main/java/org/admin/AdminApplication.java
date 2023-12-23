package org.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yxl17
 * @Package : org.example
 * @Create on : 2023/12/24 02:50
 **/

@SpringBootApplication(scanBasePackages = {"org.database", "org.commons"})
@SpringBootConfiguration
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}