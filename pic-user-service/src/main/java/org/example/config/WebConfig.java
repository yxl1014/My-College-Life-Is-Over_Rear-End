package org.example.config;

import org.example.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 解决跨域问题
 *
 * @author yxl17
 * @Package : org.example.config
 * @Create on : 2023/11/11 19:32
 **/

@Configuration
@EnableSwagger2// 开启Swagger2的自动配置
public class WebConfig implements WebMvcConfigurer {

    /**
     * 重写addCorsMappings()解决跨域问题
     * 配置：允许http请求进行跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册Interceptor拦截器(Interceptor这个类是我们自己写的拦截器类)
        InterceptorRegistration registration = registry.addInterceptor(new RoleInterceptor());
        //addPathPatterns()方法添加需要拦截的路径
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //excludePathPatterns()方法添加不拦截的路径
        //添加不拦截路径
        registration.excludePathPatterns();
    }
}
