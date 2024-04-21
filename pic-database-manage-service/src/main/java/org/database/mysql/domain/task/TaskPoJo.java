package org.database.mysql.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.GsonUtil;
import org.database.mysql.domain.task.result.TaskResult;

import java.util.List;

/**
 * @author Administrator
 * @Package : org.database.mysql.domain.task
 * @Create on : 2024/3/29 上午10:40
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskPoJo {
    private String taskId;
    private String taskAuthorId;
    private String taskName;
    private Double taskMoney;
    private Long taskCreateTime;
    private Integer taskType;
    private List<TaskShell> taskShell;
    private Integer taskState;
    private Long taskStartTime;
    private Long taskEndTime;
    private Long taskTestTime;
    private TaskResult taskTestResult;

    public TaskPoJo(Task task) {
        if (task == null) {
            return;
        }
        if (!Strings.isEmpty(task.getTaskId())) {
            this.taskId = task.getTaskId();
        }
        if (!Strings.isEmpty(task.getTaskAuthorId())) {
            this.taskAuthorId = task.getTaskAuthorId();
        }
        if (!Strings.isEmpty(task.getTaskName())){
            this.taskName = task.getTaskName();
        }
        if (task.getTaskMoney() != null) {
            this.taskMoney = task.getTaskMoney();
        }
        if (task.getTaskCreateTime() != null) {
            this.taskCreateTime = task.getTaskCreateTime();
        }
        if (task.getTaskType() != null) {
            this.taskType = task.getTaskType();
        }
        if (task.getTaskShell() != null) {
            this.taskShell = GsonUtil.listFromJson(task.getTaskShell(), TaskShell.class);
        }
        if (task.getTaskState() != null) {
            this.taskState = task.getTaskState();
        }
        if (task.getTaskStartTime() != null) {
            this.taskStartTime = task.getTaskStartTime();
        }
        if (task.getTaskEndTime() != null) {
            this.taskEndTime = task.getTaskEndTime();
        }
        if (task.getTaskTestTime() != null) {
            this.taskTestTime = task.getTaskTestTime();
        }
        if (task.getTaskTestResult() != null) {
            this.taskTestResult = GsonUtil.fromJson(task.getTaskTestResult(), TaskResult.class);
        }
    }
}
