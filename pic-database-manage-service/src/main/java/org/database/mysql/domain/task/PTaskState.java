package org.database.mysql.domain.task;

/**
 * @author Administrator
 * @Package : org.database.mysql.domain.task
 * @Create on : 2024/5/6 下午7:03
 **/

//0、等待中，1、测试中，2、暂停，3、结束测试
public enum PTaskState {
    WAITING,        // 等待中
    TESTING,        // 测试中
    PAUSE,          // 暂停
    END,            // 结束
}
