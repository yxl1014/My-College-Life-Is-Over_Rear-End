package org.database.mysql.domain.task.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {
 * {总测试时长，总测试条目数，总成功条数，总失败条数，总请求失败数量，总预计结果比对失败数量}
 * [{测试者ID，开始时间，结束时间，持续时间，测试条目数，成功数量，失败数量，请求失败数量，预计结果比对失败数量，失败消息体}]
 * }
 * @author Administrator
 * @Package : org.database.mysql.domain.task
 * @Create on : 2024/3/29 上午10:08
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResult {
    private Long allTestTime;
    private Long allTestCount;
    private Long allSuccessCount;
    private Long allFailCount;
    private Long allReqFailCount;
    private Long allExpectedFailCount;
    private List<TaskUserResult> allUserResults;
}
