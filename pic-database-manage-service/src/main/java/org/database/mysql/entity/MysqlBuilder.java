package org.database.mysql.entity;

import lombok.Data;

/**
 * @author yxl17
 * @Package : org.mysql.entity
 * @Create on : 2023/12/17 16:21
 **/

@Data
public class MysqlBuilder<T> {
    private MysqlOptType optType;//mysql操作类型的枚举值
    private MysqlResultType resultType;//mysql查询结果的类型
    private Class<T> clz;//泛型类型的Class对象
    private T condition;//查询条件的IN子句
    private T noEqual;//查询条件中的！=子句
    private T out;//查询条件中的out子句
    private T update;//表示更新操作的数据
    private boolean page;//是否分页
    private int pageIndex;//页下标
    private int pageSize;//页大小
    private ConditionType queryType = ConditionType.EQ;//是否模糊


    public MysqlBuilder(Class<T> clz, T condition, T out) {
        this.clz = clz;
        this.condition = condition;
        this.out = out;
    }
    //接收了一个泛型类型的Class对象，in和out参数，并将它们赋值给对应的属性


    public MysqlBuilder(Class<T> clz) {
        this.clz = clz;
        this.condition = null;
        this.out = null;
    }

    //接收了一个泛型的class对象，并将其赋值给clz属性，同时将in和out设置为null

    public MysqlBuilder<T> buildIn(T condition) {
        this.condition = condition;
        return this;
    }
    //设置查询条件中=子句，并返回当前对象

    public MysqlBuilder<T> buildNoEqual(T noEqual) {
        this.noEqual = noEqual;
        return this;
    }
    //设置查询条件中！=子句，并返回当前对象

    public MysqlBuilder<T> buildOut(T out) {
        this.out = out;
        return this;
    }
    //设置查询条件中out子句，并返回当前对象

    public MysqlBuilder<T> buildUpdate(T update) {
        this.update = update;
        return this;
    }


    //设置更新操作的数据，并返回当前对象
}

//通过MysqlBuilder类，可以构建mysql查询或更新操作的条件。
// 由于是泛型类，可以适用于不同的数据类型
