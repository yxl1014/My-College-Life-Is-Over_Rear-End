package org.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yxl17
 * @Package : org.mysql
 * @Create on : 2023/12/17 17:23
 **/

@SpringBootApplication
@SpringBootConfiguration
public class MysqlTestApplication {
    public static void main(String[] args) {

        SpringApplication.run(MysqlTestApplication.class, args);
    }
}