package org.commons.domain.constData;

/**
 * 一些数字的常量
 *
 * @author yxl17
 * @Package : org.commons.domain.constData
 * @Create on : 2024/1/3 20:53
 **/
public class MagicMathConstData {

    /**
     * 毫秒级的用户通用数据有效期 3天
     */
    public static final int MAGIC_MAP_DEFAULT_SIZE = 16;

    /**
     * 毫秒级的用户通用数据有效期 3天
     */
    public static final long MAGIC_LOGIN_COMMON_DATA_TIME_MS = 3 * 24 * 60 * 60 * 1000;

    /**
     * 秒级的用户通用数据有效期 3天
     */
    public static final long MAGIC_LOGIN_COMMON_DATA_TIME = 3 * 24 * 60 * 60;


    /**
     * 毫秒级的用户通用数据有效期 1分钟
     */
    public static final long MAGIC_VERITY_CODE_TIME_MS = 60 * 1000;

    /**
     * 秒级的用户通用数据有效期 1分钟
     */
    public static final long MAGIC_VERITY_CODE_TIME = 60;

    /**
     * 秒级的用户通用数据有效期 3分钟
     */
    public static final long MAGIC_VERITY_CODE_TIME_3_MIN = 60 * 3;

    /**
     * redis无需value常量
     */
    public static final String MAGIC_REDIS_TIMEOUT = "1";
}
