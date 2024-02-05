package org.commons.annotation;

import org.commons.domain.RoleType;

import java.lang.annotation.*;

/**
 * @author yxl17
 * @Package : org.commons.annotation
 * @Create on : 2023/12/24 16:58
 **/

@Target({ElementType.PARAMETER, ElementType.METHOD}) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented//生成文档
public @interface ControllerLog {

    String url() default "/";

    String msg() default " ";

    RoleType roleType() default RoleType.PROVIDER;
}
