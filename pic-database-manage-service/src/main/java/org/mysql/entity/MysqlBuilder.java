package org.mysql.entity;

import lombok.Data;

/**
 * @author yxl17
 * @Package : org.mysql.entity
 * @Create on : 2023/12/17 16:21
 **/

@Data
public class MysqlBuilder<T> {
    private MysqlOptType optType;
    private MysqlResultType resultType;
    private Class<T> clz;
    private T in;
    private T noEqual;
    private T out;
    private T update;

    public MysqlBuilder(Class<T> clz, T in, T out) {
        this.clz = clz;
        this.in = in;
        this.out = out;
    }

    public MysqlBuilder(Class<T> clz) {
        this.clz = clz;
        this.in = null;
        this.out = null;
    }

    public MysqlBuilder<T> buildIn(T in) {
        this.in = in;
        return this;
    }

    public MysqlBuilder<T> buildNoEqual(T noEqual) {
        this.noEqual = noEqual;
        return this;
    }

    public MysqlBuilder<T> buildOut(T out) {
        this.out = out;
        return this;
    }

    public MysqlBuilder<T> buildUpdate(T update) {
        this.update = update;
        return this;
    }
}
