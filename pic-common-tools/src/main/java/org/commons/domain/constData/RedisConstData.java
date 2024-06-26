package org.commons.domain.constData;

/**
 * redis key 前缀静态对象
 *
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

    /**
     * 用户在线
     */
    public static final String USER_ONLINE = "e";

    /**
     * 电话  UUID:tel ttl:60s
     */
    public static final String VERITY_TEL = "f";

    /**
     * 邮箱  UUID:email ttl:60s
     */
    public static final String VERITY_EMAIL = "g";

    /**
     * 验证冷却  KEY:CONST ttl:60s  ket:tel、email
     */
    public static final String VERITY_TIMEOUT = "h";

    /**
     * 找回密码 验证用户名->验证手机或邮箱或者密保问题
     */
    public static final String FIND_PASSWORD_1 = "i";

    /**
     * 找回密码 验证手机或邮箱或者密保问题->输入新密码
     */
    public static final String FIND_PASSWORD_2 = "j";
}
