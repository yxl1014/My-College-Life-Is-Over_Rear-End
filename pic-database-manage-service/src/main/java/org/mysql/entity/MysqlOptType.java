package org.mysql.entity;

/**
 * @author yxl17
 * @Package : org.mysql.entity
 * @Create on : 2023/12/17 16:19
 **/
public enum MysqlOptType {
    //添加
    INSERT,
    //修改
    UPDATE,
    //删除
    DELETE,
    //查询
    SELECT,
    ;
}
//定义枚举类型可以在代码更清晰表示不同的操作类型
//避免使用字符串或者数字表示操作类型
//每个枚举常量都是唯一的，可以通过名称进行访问
//可以用MysqlOptType.INSERT可以表示添加操作

