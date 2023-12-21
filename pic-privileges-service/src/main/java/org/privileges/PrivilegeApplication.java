package org.privileges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author eensh
 */
@SpringBootApplication(scanBasePackages = {"org.mysql","org.privileges"})
@SpringBootConfiguration
public class PrivilegeApplication {
    public static void main(String[] args) {

        SpringApplication.run(PrivilegeApplication.class, args);
    }
}

