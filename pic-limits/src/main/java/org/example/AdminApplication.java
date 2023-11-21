package org.example;
import org.example.model.dao.TestMybatisMapper;
import org.example.service.Impl.TestMybatisServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @Author: eensh
 * @CreateDate: 2023/11/16
 */// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableScheduling
@Configuration
public class AdminApplication {

    public static void main(String[] args)
    {

        SpringApplication.run(AdminApplication.class, args);
    }
}
