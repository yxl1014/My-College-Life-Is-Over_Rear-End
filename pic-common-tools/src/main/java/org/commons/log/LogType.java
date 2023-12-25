package org.commons.log;

/**
 * @author yxl17
 * @Package : org.example.log
 * @Create on : 2023/11/11 20:23
 **/
public enum LogType {
    //测试
    TEST("测试一下"),
    SYSTEM("系统内部异常"),
    //aop
    LOGIN("登录操作"),
    REGISTER("注册操作"),
    INTERCEPTOR("拦截器"),
    FILTER("过滤操作"),
    IDENTITY("验证操作"),
    UTIL("工具类"),
    MYSQL("数据库"),
    PUBLIC_CONTEXT("核心逻辑区"),
    ACCESS_DATA_INTERFACE("访问数据接口操作"),
    KAFKA("KAFKA"),
    SCHEDULED("定时器"),
    CONTROL("调度器"),
    ELASTICSEARCH("es"),
    POWER("权限操作"),
    USER("角色操作"),
    ROLE("角色操作");


    private String name;

    LogType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
