package org.database.mysql.domain.task;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.GsonUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_task")
public class Task {
    @TableId
    private String taskId;
    private String taskAuthorId;
    private String taskName;
    private Double taskMoney;
    private Long taskCreateTime;
    private Integer taskType;
    private String taskShell;
    private Integer taskState;
    private Long taskStartTime;
    private Long taskEndTime;
    private Long taskTestTime;
    private String taskTestResult;

    public Task(TaskPoJo poJo) {
        if (poJo == null) {
            return;
        }
        if (!Strings.isEmpty(poJo.getTaskId())) {
            this.taskId = poJo.getTaskId();
        }
        if (!Strings.isEmpty(poJo.getTaskAuthorId())) {
            this.taskAuthorId = poJo.getTaskAuthorId();
        }
        if (!Strings.isEmpty(poJo.getTaskName())){
            this.taskName = poJo.getTaskName();
        }
        if (poJo.getTaskMoney() != null) {
            this.taskMoney = poJo.getTaskMoney();
        }
        if (poJo.getTaskCreateTime() != null) {
            this.taskCreateTime = poJo.getTaskCreateTime();
        }
        if (poJo.getTaskType() != null) {
            this.taskType = poJo.getTaskType();
        }
        if (poJo.getTaskShell() != null) {
            this.taskShell = GsonUtil.toJson(poJo.getTaskShell());
        }
        if (poJo.getTaskState() != null) {
            this.taskState = poJo.getTaskState();
        }
        if (poJo.getTaskStartTime() != null) {
            this.taskStartTime = poJo.getTaskStartTime();
        }
        if (poJo.getTaskEndTime() != null) {
            this.taskEndTime = poJo.getTaskEndTime();
        }
        if (poJo.getTaskTestTime() != null) {
            this.taskTestTime = poJo.getTaskTestTime();
        }
        if (poJo.getTaskTestResult() != null) {
            this.taskTestResult = GsonUtil.toJson(poJo.getTaskTestResult());
        }
    }

    public void clone(TaskPoJo poJo){
        if (poJo == null) {
            return;
        }
        if (!Strings.isEmpty(poJo.getTaskId())) {
            this.taskId = poJo.getTaskId();
        }
        if (!Strings.isEmpty(poJo.getTaskAuthorId())) {
            this.taskAuthorId = poJo.getTaskAuthorId();
        }
        if (poJo.getTaskMoney() != null) {
            this.taskMoney = poJo.getTaskMoney();
        }
        if (poJo.getTaskCreateTime() != null) {
            this.taskCreateTime = poJo.getTaskCreateTime();
        }
        if (poJo.getTaskType() != null) {
            this.taskType = poJo.getTaskType();
        }
        if (poJo.getTaskShell() != null) {
            this.taskShell = GsonUtil.toJson(poJo.getTaskShell());
        }
        if (poJo.getTaskState() != null) {
            this.taskState = poJo.getTaskState();
        }
        if (poJo.getTaskStartTime() != null) {
            this.taskStartTime = poJo.getTaskStartTime();
        }
        if (poJo.getTaskEndTime() != null) {
            this.taskEndTime = poJo.getTaskEndTime();
        }
        if (poJo.getTaskTestTime() != null) {
            this.taskTestTime = poJo.getTaskTestTime();
        }
        if (poJo.getTaskTestResult() != null) {
            this.taskTestResult = GsonUtil.toJson(poJo.getTaskTestResult());
        }
    }

    public Task(String taskId) {
        this.taskId = taskId;
    }
}
