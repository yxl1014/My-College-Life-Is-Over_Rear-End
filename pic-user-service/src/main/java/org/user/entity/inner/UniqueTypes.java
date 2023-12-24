package org.user.entity.inner;

/**
 * @description: 三种验证身份方式的枚举
 * @author: HammerRay
 * @date: 2023/12/3 下午9:52
 */
public enum UniqueTypes {
    /**
     * 用户名
     */
    USERNAME("用户名"),
    /**
     * 电话号码
     */
    TELEPHONE("电话号码"),
    /**
     * 邮箱地址
     */
    EMAIL("邮箱地址");

    private String data;

    UniqueTypes(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
