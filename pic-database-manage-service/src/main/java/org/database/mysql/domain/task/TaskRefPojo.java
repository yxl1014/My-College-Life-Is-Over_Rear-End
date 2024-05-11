package org.database.mysql.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.commons.common.GsonUtil;
import org.database.mysql.domain.TaskUserRef;

import java.util.List;

/**
 * @author Administrator
 * @Package : org.database.mysql.domain.task
 * @Create on : 2024/4/22 14:28
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRefPojo {
    private String taskId;
    private String taskAuthorId;
    private String taskName;
    private Double taskMoney;
    private Long taskCreateTime;
    private Integer taskType;
    private List<TaskShell> taskShell;
    private Integer taskState;
    private Long refAllReq;
    private Long refSuccessReq;
    private Long refFailedReq;
    private Long refFailedCode;
    private Long refFailedTarget;
    private Long refStartTime;
    private Long refEndTime;
    private Long refTestTime;
    private Integer refState;

    public TaskRefPojo(TaskUserRef ref) {
        Build(ref);
    }

    public TaskRefPojo(Task task) {
        Build(task);
    }

    public TaskRefPojo(TaskPoJo task) {
        Build(task);
    }

    public TaskRefPojo(TaskUserRef ref, Task task) {
        Build(ref);
        Build(task);
    }

    public TaskRefPojo(TaskUserRef ref, TaskPoJo task) {
        Build(ref);
        Build(task);
    }

    public void Build(TaskPoJo task)
    {
        if (task.getTaskId() != null) {
            this.taskId = task.getTaskId();
        }
        if (task.getTaskAuthorId() != null) {
            this.taskAuthorId = task.getTaskAuthorId();
        }
        if (task.getTaskName() != null) {
            this.taskName = task.getTaskName();
        }
        if (task.getTaskMoney() != null) {
            this.taskMoney = task.getTaskMoney();
        }
        if (task.getTaskType() != null) {
            this.taskType = task.getTaskType();
        }
        if (task.getTaskShell() != null) {
            this.taskShell = task.getTaskShell();
        }
        if (task.getTaskState() != null) {
            this.taskState = task.getTaskState();
        }
        if (task.getTaskCreateTime() != null) {
            this.taskCreateTime = task.getTaskCreateTime();
        }
    }

    public void Build(TaskUserRef ref) {
        if (ref.getRefAllReq() != null) {
            this.refAllReq = ref.getRefAllReq();
        }
        if (ref.getRefSuccessReq() != null) {
            this.refSuccessReq = ref.getRefSuccessReq();
        }
        if (ref.getRefFailedReq() != null) {
            this.refFailedReq = ref.getRefFailedReq();
        }
        if (ref.getRefFailedCode() != null) {
            this.refFailedCode = ref.getRefFailedCode();
        }
        if (ref.getRefFailedTarget() != null) {
            this.refFailedTarget = ref.getRefFailedTarget();
        }
        if (ref.getRefStartTime() != null) {
            this.refStartTime = ref.getRefStartTime();
        }
        if (ref.getRefEndTime() != null) {
            this.refEndTime = ref.getRefEndTime();
        }
        if (ref.getRefTestTime() != null) {
            this.refTestTime = ref.getRefTestTime();
        }
        if (ref.getRefState() != null) {
            this.refState = ref.getRefState();
        }
    }

    public void Build(Task task) {
        if (task.getTaskId() != null) {
            this.taskId = task.getTaskId();
        }
        if (task.getTaskAuthorId() != null) {
            this.taskAuthorId = task.getTaskAuthorId();
        }
        if (task.getTaskName() != null) {
            this.taskName = task.getTaskName();
        }
        if (task.getTaskMoney() != null) {
            this.taskMoney = task.getTaskMoney();
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
        if (task.getTaskCreateTime() != null) {
            this.taskCreateTime = task.getTaskCreateTime();
        }
    }
}
