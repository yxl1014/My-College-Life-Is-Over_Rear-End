package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author eensh
 */
@SpringBootApplication(scanBasePackages = {"org.mysql"})
@SpringBootConfiguration
public class PrivilegeApplication {
    public static void main(String[] args) {

        SpringApplication.run(PrivilegeApplication.class, args);
    }
}

