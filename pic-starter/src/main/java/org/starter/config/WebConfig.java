package org.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.starter.interceptor.CheckTokenInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yxl17
 * @Package : org.starter.config
 * @Create on : 2023/12/24 17:00
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/**");
        String[] swaggerExcludePathPatterns = {"/doc.html", "/swagger**/**", "/swagger-resources/**", "/webjars/**", "/v3/**"};
        registry.addInterceptor(checkTokenInterceptor).addPathPatterns(list)
                .excludePathPatterns(swaggerExcludePathPatterns);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 匹配所有的路径
                .allowCredentials(true) // 设置允许凭证
                .allowedHeaders("*")   // 设置请求头
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 设置允许的方式
                .allowedOriginPatterns("*");
    }
}