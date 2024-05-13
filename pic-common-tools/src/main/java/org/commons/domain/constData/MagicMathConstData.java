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


    /**
     * 默认队列大小
     */
    public static final int DEFAULT_QUEUE_CAPACITY = 1024;


    /*
     *  任务测试结果接受队列前缀
     * */
    public static final String TASK_TEST_RESULT_OPT_QUEUE_PREFIX = "TTROQP";

    /*
     *  关闭任务测试结果队列线程名前缀
     * */
    public static final String TASK_TEST_RESULT_CLOSE_THREAD_PREFIX = "TTRCTP";


    /**
     * 关闭任务队列多让他处理十秒，如果十秒处理不完也丢弃
     */
    public static final long TASK_MORE_CLOSE_TIME = 10000;


    /**
     * 间断测试时间间隔 5s
     */
    public static final long TASK_TO_TEST_TIME = 5000;


    /**
     * 线程工厂任务结果集处理线程前缀
     */
    public static final String TASK_RESULT_OPTION_THREAD_PREFIX = "TROTP";

}
