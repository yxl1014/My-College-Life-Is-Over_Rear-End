package org.database.mysql.domain.task.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {测试者ID，开始时间，结束时间，持续时间，测试条目数，成功数量，失败数量，请求失败数量，预计结果比对失败数量，失败消息体}
 * @author Administrator
 * @Package : org.database.mysql.domain.task.result
 * @Create on : 2024/3/29 上午10:14
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserResult {
    private String testUserId;
    private long testStartTime;
    private long testEndTime;
    private long testTime;
    private long testCount;
    private long testSuccessCount;
    private long testFailCount;
    private long testReqFailCount;
    private long testExpectedFailCount;
    private String testFailMsg;
}
