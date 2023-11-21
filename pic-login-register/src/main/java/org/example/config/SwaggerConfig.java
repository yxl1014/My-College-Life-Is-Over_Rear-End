package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;

import java.util.ArrayList;

/**
 *访问地址：<a href="http://localhost:8080/swagger-ui/index.html">...</a>
 * @description:
 * @author: HammerRay
 * @date: 2023/11/5 下午10:30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //方式1，2都可接入
                .apiInfo(apiInfo())
                //如果为false浏览器不可以访问
                .enable(true)
                //通过.select方法去配置扫描接口
                .select()
                // 替换成你的控制器包名
                .apis(RequestHandlerSelectors.basePackage("org.example.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //方式一
    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder()
                .title("swagger-api文档")
                .description("swagger接入教程")
                //服务条款网址
                .version("1.0")
                .build();
    }

    //方式二
    private ApiInfo apiInfo() {
        Contact contact = new Contact("赵阳", "http://localhost:10086/#/", "xxx@163.com");
        return new ApiInfo(
                // 标题
                "Swagger测试案例",
                // 描述
                "学习演示如何配置Swagger",
                // 版本
                "v1.0",
                // 组织链接
                "https://blog.csdn.net/m0_59278919?type=collect",
                // 联系人信息
                contact,
                // 许可
                "Apach 2.0",
                // 许可连接
                "https://blog.csdn.net/m0_59278919?type=collect",
                // 扩展
                new ArrayList<>()
        );
    }

}
