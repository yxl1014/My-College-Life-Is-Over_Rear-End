package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @description:
 * @author: HammerRay
 * @date:2023/11/3
 */
@SpringBootApplication
@SpringBootConfiguration
public class LoginRegisterApplication {
    public static void main(String []args){
        SpringApplication.run(LoginRegisterApplication.class);
    }
}
