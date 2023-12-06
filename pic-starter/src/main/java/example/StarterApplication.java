package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 *
 * @description:
 * @author: HammerRay
 * @date: 2023/11/3
 */
@SpringBootApplication
@SpringBootConfiguration
@EnableCaching
public class StarterApplication {
    public static void main(String []args){
        SpringApplication.run(StarterApplication.class);
    }
}
