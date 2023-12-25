package org.commons.domain.constData;

/**
 * redis key 前缀静态对象
 * @author yxl17
 * @Package : org.commons.domain.constData
 * @Create on : 2023/12/24 18:14
 **/
public class RedisConstData {
    // 这里写abc是为了省redis 的空间 只要确保这个前缀唯一那么 就是可以的 当然 你可以写明确了


    /**
     * 用户验证数据的version 存在redis里
     */
    public static final String USER_LOGIN_VERSION = "a";

    /**
     * 图片验证码  UUID:CODE ttl:60s
     */
    public static final String VERITY_PICTURE_CODE = "b";

    /**
     * 电话验证码  UUID:CODE ttl:60s
     */
    public static final String VERITY_TEL_CODE = "c";

    /**
     * 邮箱验证码  UUID:CODE ttl:60s
     */
    public static final String VERITY_EMAIL_CODE = "d";
}
