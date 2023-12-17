package org.mysql.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 这个东西是 同一个库结构 然后有多个库 桶hash平均分配做的 所以这里我们没用
 * @author yxl17
 * @Package : org.mysql.config
 * @Create on : 2023/12/17 14:33
 **/
//@Configuration
public class MybatisPlusConfig {
    /*public static ThreadLocal<String> TableName = new ThreadLocal<>();

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        HashMap<String, TableNameHandler> map = new HashMap<String, TableNameHandler>(2) {{
            put(TableName.get(), ((sql, tableName) -> TableName.get()));
        }};
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
        mybatisPlusInterceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        TableName.remove();
        return mybatisPlusInterceptor;
    }*/
}
