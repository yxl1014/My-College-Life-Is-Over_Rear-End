package org.commons.common.net;

import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @Package : org.commons.common.net
 * @Create on : 2024/4/2 上午10:25
 **/

@Configuration
public class HttpConfig {

    @Bean
    public PoolingHttpClientConnectionManager GetHttpClientConnectionManager() {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(200);
        // 设置每个路由的默认最大连接
        cm.setDefaultMaxPerRoute(20);
        return cm;
    }
}
