package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author eensh
 */
@SpringBootApplication
@SpringBootConfiguration

public class PrivilegeApplication {
    public static void main(String[] args) {

        SpringApplication.run(PrivilegeApplication.class, args);
    }
}

