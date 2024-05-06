package org.task.service.impl;

import lombok.SneakyThrows;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.GsonUtil;
import org.commons.common.ThreadLocalComp;
import org.commons.common.random.VerifyCodeGenerator;
import org.commons.common.uuid.UuidGenerator;
import org.commons.domain.LoginCommonData;
import org.commons.log.LogComp;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
import org.database.mysql.domain.task.Task;
import org.database.mysql.domain.task.TaskPoJo;
import org.database.mysql.domain.task.TaskShell;
import org.database.mysql.domain.task.TaskState;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.service.TaskMysqlComp;
import org.database.mysql.service.UserMysqlComp;
import org.springframework.stereotype.Service;
import org.task.common.net.TaskShellCheckComp;
import org.task.entity.TaskQueryRequest;
import org.task.service.ITaskBaseService;
import org.task.service.ITaskConsumerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yxl17
 * @Package : org.task.service.impl
 * @Create on : 2024/3/25 20:19
 **/
@Service
public class TaskConsumerServiceImpl implements ITaskConsumerService {

    private final Logger logger = LogComp.getLogger(TaskConsumerServiceImpl.class);

    private final UserMysqlComp userMysqlComp;

    private final TaskMysqlComp taskMysqlComp;

    private final BaseMysqlComp baseMysqlComp;

    private final ITaskBaseService taskBaseService;

    private final TaskShellCheckComp shellCheckComp;

    private final ThreadLocalComp threadLocalComp;

    public TaskConsumerServiceImpl(UserMysqlComp userMysqlComp, TaskMysqlComp taskMysqlComp, BaseMysqlComp baseMysqlComp, ITaskBaseService taskBaseService, ThreadLocalComp threadLocalComp, TaskShellCheckComp shellCheckComp) {
        this.userMysqlComp = userMysqlComp;
        this.taskMysqlComp = taskMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
        this.taskBaseService = taskBaseService;
        this.threadLocalComp = threadLocalComp;
        this.shellCheckComp = shellCheckComp;
    }

    @SneakyThrows
    @Override
    public ReBody createTask(TaskPoJo taskPoJo) {
        if (Strings.isEmpty(taskPoJo.getTaskAuthorId())) {
            return new ReBody(RepCode.R_ParamNull);
        }

        if (taskPoJo.getTaskType() < 0 || taskPoJo.getTaskType() > 2) {
            return new ReBody(RepCode.R_ParamNull);
        }

        User user = userMysqlComp.findUserByUserId(taskPoJo.getTaskAuthorId());
        if (user == null) {
            return new ReBody(RepCode.R_UserNotFound);
        }

        // 用户权限限制接口 因此不需要进行权限校验
        if (user.getUserMoney() < taskPoJo.getTaskMoney()) {
            return new ReBody(RepCode.R_UserMoneyNotEnough);
        }
        // 遵循不损害项目利益的顺序执行 先执行扣钱， 再添加任务
        User update = new User(user.getUserId());
        update.setUserMoney(user.getUserMoney() - taskPoJo.getTaskMoney());
        boolean success = userMysqlComp.updateUserByUserId(update);
        if (!success) {
            return new ReBody(RepCode.R_UpdateDbFailed);
        }

        Task task = new Task(taskPoJo);
        String taskId = UuidGenerator.getCustomUuid();
        task.setTaskId(taskId);
        task.setTaskCreateTime(System.currentTimeMillis());

        //如果任务名为null 就给他随机生成一个
        if (Strings.isEmpty(task.getTaskName())) {
            task.setTaskName("TestTask" + VerifyCodeGenerator.genNumberVerityCode());
        }
        //初始化任务状态
        task.setTaskState(TaskState.PLANNING.ordinal());

        MysqlBuilder<Task> taskMysql = new MysqlBuilder<>(Task.class);
        taskMysql.setCondition(task);
        Integer suc = baseMysqlComp.insert(taskMysql);
        if (suc != 1) {
            return new ReBody(RepCode.R_Fail);
        }
        return new ReBody(RepCode.R_Ok, taskId);
    }

    @Override
    public ReBody updateTaskShell(TaskPoJo taskPoJo) {
        if (Strings.isEmpty(taskPoJo.getTaskId())
                || taskPoJo.getTaskShell() == null || taskPoJo.getTaskShell().isEmpty()) {
            return new ReBody(RepCode.R_ParamNull);
        }
        Task task = taskMysqlComp.selectTaskByTaskId(taskPoJo.getTaskId());
        if (task == null) {
            return new ReBody(RepCode.R_TaskNotFound);
        }
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (!task.getTaskAuthorId().equals(commonData.getUserId())) {
            return new ReBody(RepCode.R_UserNotIsThisTaskAuthor);
        }
        // 判断任务状态 只有在开始前可以修改
        if (task.getTaskState() != TaskState.PLANNING.ordinal()) {
            return new ReBody(RepCode.R_TaskIsRunning);
        }

        HashMap<Integer, Boolean> successMap = new HashMap<>();

        for (TaskShell taskShell : taskPoJo.getTaskShell()) {
            boolean success = shellCheckComp.checkTaskShell(taskShell);
            successMap.put(taskShell.getShellIndex(), success);
        }

        Task update = new Task();
        update.setTaskId(taskPoJo.getTaskId());
        update.setTaskShell(GsonUtil.toJson(taskPoJo.getTaskShell()));
        boolean success = taskMysqlComp.updateTaskByTaskId(update);
        if (!success) {
            return new ReBody(RepCode.R_UpdateDbFailed);
        }
        return new ReBody(RepCode.R_Ok, successMap);
    }

    @Override
    public ReBody listTasks(List<String> taskIds) {
        return taskBaseService.listTasks(taskIds);
    }

    @Override
    public ReBody listConsumerTask(TaskQueryRequest queryRequest) {
        Task task = new Task(queryRequest.getTaskPoJo());
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null) {
            return new ReBody(RepCode.R_LoginTimeout);
        }
        task.setTaskAuthorId(commonData.getUserId());
        List<Task> tasks = taskMysqlComp.selectTasks(task, queryRequest.getPage(), queryRequest.getPageSize());
        List<TaskPoJo> result = new ArrayList<>();
        for (Task task1 : tasks) {
            TaskPoJo taskPoJo = new TaskPoJo(task1);
            result.add(taskPoJo);
        }
        return new ReBody(RepCode.R_Ok, result);
    }

    @Override
    public ReBody updateTaskState(TaskPoJo poJo) {
        Integer taskState = poJo.getTaskState();
        if (Strings.isEmpty(poJo.getTaskId()) || taskState == null) {
            return new ReBody(RepCode.R_ParamNull);
        }
        Task task = taskMysqlComp.selectTaskByTaskId(poJo.getTaskId());
        if (task == null) {
            return new ReBody(RepCode.R_TaskNotFound);
        }
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (!task.getTaskAuthorId().equals(commonData.getUserId())) {
            return new ReBody(RepCode.R_UserNotIsThisTaskAuthor);
        }
        // 如果本来就是直接返回成功
        if (task.getTaskState().equals(taskState)) {
            return new ReBody(RepCode.R_Ok);
        }
        if (taskState < 0 || taskState >= TaskState.values().length) {
            return new ReBody(RepCode.R_ParamError);
        }
        RepCode code = updateTaskState(TaskState.values()[taskState], task);
        Task newTask = taskMysqlComp.selectTaskByTaskId(task.getTaskId());
        return new ReBody(code, newTask);
    }

    public RepCode updateTaskState(TaskState state, Task task) {
        Integer taskState = task.getTaskState();
        TaskState nowState = TaskState.values()[taskState];
        // 允许状态从大变小的就一种清空 暂停->测试中
        if (state.ordinal() < taskState) {
            if (state != TaskState.TESTING || nowState != TaskState.PAUSE) {
                return RepCode.R_TaskStateUpdateError;
            }
            updateTaskStateFromPauseToTesting(task);
        } else {
            if (nowState == TaskState.TESTING) {
                switch (state) {
                    case PAUSE:
                        updateTaskStateFromTestingToPause(task);
                        break;
                    case END:
                        updateTaskStateFromTestingToEnd(task);
                        break;
                }
            } else if (state == TaskState.TESTING) {
                updateTaskStateFromPauseToTesting(task);
            }
        }

        //更新数据库
        Task update = new Task();
        update.setTaskId(task.getTaskId());
        update.setTaskState(state.ordinal());
        boolean success = taskMysqlComp.updateTaskByTaskId(update);
        return success ? RepCode.R_Ok : RepCode.R_UpdateDbFailed;
    }

    /**
     * 任务状态从暂停到测试中
     *
     * @param task 任务
     */
    private void updateTaskStateFromPauseToTesting(Task task) {
        logger.info("任务状态从暂停到测试中");
    }

    /**
     * 任务状态从测试中到暂停
     *
     * @param task 任务
     */
    private void updateTaskStateFromTestingToPause(Task task) {

        logger.info("任务状态从测试中到暂停");
    }

    /**
     * 任务状态从测试中到结束
     *
     * @param task 任务
     */
    private void updateTaskStateFromTestingToEnd(Task task) {
        logger.info("任务状态从测试中到结束");
    }
}
