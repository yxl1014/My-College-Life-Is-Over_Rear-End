package org.database.mysql.domain.task.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.database.mysql.domain.TaskUserRef;

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
    private Long testTime;
    private Long testCount;
    private Long testSuccessCount;
    private Long testFailCount;
    private Long testReqFailCount;
    private Long testExpectedFailCount;

    public TaskUserResult(TaskUserRef userRef){
        this.testUserId = userRef.getRefUserId();
        this.testTime = userRef.getRefTestTime();
        this.testCount = userRef.getRefAllReq();
        this.testSuccessCount = userRef.getRefSuccessReq();
        this.testFailCount = userRef.getRefFailedReq();
        this.testReqFailCount = userRef.getRefFailedCode();
        this.testExpectedFailCount = userRef.getRefFailedTarget();
    }
}
