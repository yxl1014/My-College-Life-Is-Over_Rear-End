package org.privileges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author eenshs
 */
@SpringBootApplication(scanBasePackages = {"org.database", "org.privileges"})
@SpringBootConfiguration
public class PrivilegeApplication {
    public static void main(String[] args) {

        SpringApplication.run(PrivilegeApplication.class, args);
    }
}

