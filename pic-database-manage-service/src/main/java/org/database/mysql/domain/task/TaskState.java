package org.database.mysql.domain.task;

/**
 * @author Administrator
 * @Package : org.database.mysql.domain.task
 * @Create on : 2024/4/19 下午5:19
 **/

// 0、规划中，1、报名中，2、测试中，3、暂停，4、结束
public enum TaskState {
    PLANNING,       // 规划中
    REGISTER,       // 报名中
    TESTING,        // 测试中
    PAUSE,          // 暂停
    END,            // 结束
}
