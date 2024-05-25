package org.task.service.impl;

import lombok.SneakyThrows;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.async.LocalThreadFactory;
import org.async.runnable.TaskResultOptRunnable;
import org.commons.common.GsonUtil;
import org.commons.common.ThreadLocalComp;
import org.commons.common.random.VerifyCodeGenerator;
import org.commons.common.uuid.UuidGenerator;
import org.commons.domain.LoginCommonData;
import org.commons.domain.constData.MagicMathConstData;
import org.commons.log.LogComp;
import org.commons.log.LogType;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.TaskUserRef;
import org.database.mysql.domain.User;
import org.database.mysql.domain.task.*;
import org.database.mysql.domain.task.result.TaskResult;
import org.database.mysql.domain.task.result.TaskUserResult;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.service.TaskMysqlComp;
import org.database.mysql.service.TaskRefMysqlComp;
import org.database.mysql.service.UserMysqlComp;
import org.mq.MyQueue;
import org.mq.QueueFactory;
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


    private final QueueFactory queueFactory;
    private final TaskRefMysqlComp taskRefMysqlComp;

    private final LocalThreadFactory threadFactory;

    public TaskConsumerServiceImpl(UserMysqlComp userMysqlComp, TaskMysqlComp taskMysqlComp, BaseMysqlComp baseMysqlComp, ITaskBaseService taskBaseService, ThreadLocalComp threadLocalComp, TaskShellCheckComp shellCheckComp, QueueFactory queueFactory, TaskRefMysqlComp taskRefMysqlComp, LocalThreadFactory threadFactory) {
        this.userMysqlComp = userMysqlComp;
        this.taskMysqlComp = taskMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
        this.taskBaseService = taskBaseService;
        this.threadLocalComp = threadLocalComp;
        this.shellCheckComp = shellCheckComp;
        this.queueFactory = queueFactory;
        this.taskRefMysqlComp = taskRefMysqlComp;
        this.threadFactory = threadFactory;
    }

    @SneakyThrows
    @Override
    public ReBody createTask(TaskPoJo taskPoJo) {
        if (taskPoJo.getTaskType() < 0 || taskPoJo.getTaskType() > 2) {
            return new ReBody(RepCode.R_ParamNull);
        }

        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null) {
            return new ReBody(RepCode.R_TokenError);
        }

        String userId = commonData.getUserId();

        User user = userMysqlComp.findUserByUserId(userId);
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
        task.setTaskAuthorId(user.getUserId());
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

    @Override
    public void onServerClose() {
        Task in = new Task();
        in.setTaskState(TaskState.TESTING.ordinal());
        List<Task> tasks = taskMysqlComp.selectTasks(in);
        for (Task task : tasks) {
            updateTaskStateToPause(task, false);
            LogComp.LogMessage builder = LogComp.buildData(LogType.TASK);
            builder.build("taskId", task.getTaskId());
            builder.build("server close update task state");
            logger.info(builder.log());
        }
    }

    public RepCode updateTaskState(TaskState state, Task task) {
        Integer taskState = task.getTaskState();
        TaskState nowState = TaskState.values()[taskState];
        RepCode code = RepCode.R_Ok;
        // 允许状态从大变小的就一种清空 暂停->测试中
        if (state.ordinal() < taskState) {
            if (state != TaskState.TESTING || nowState != TaskState.PAUSE) {
                return RepCode.R_TaskStateUpdateError;
            }
            code = updateTaskStateToTesting(task);
        } else {
            if (nowState == TaskState.TESTING) {
                switch (state) {
                    case PAUSE:
                        code = updateTaskStateToPause(task, false);
                        break;
                    case END:
                        code = updateTaskStateToEnd(task, taskState == TaskState.PAUSE.ordinal());
                        break;
                }
            } else if (state == TaskState.TESTING) {
                code = updateTaskStateToTesting(task);
            } else if (nowState == TaskState.PLANNING) {
                //更新数据库
                Task update = new Task();
                update.setTaskId(task.getTaskId());
                update.setTaskState(TaskState.REGISTER.ordinal());
                boolean success = taskMysqlComp.updateTaskByTaskId(update);
                if (!success) {
                    return RepCode.R_UpdateDbFailed;
                }
                return RepCode.R_Ok;
            }
        }
        return code;
    }

    /**
     * 任务状态从暂停到测试中
     * 1、创建接受测试结果的队列
     * 2、创建收集并处理结果的线程
     * 3、修改任务开始时间和状态
     *
     * @param task 任务
     */
    private RepCode updateTaskStateToTesting(Task task) {
        logger.info("任务状态到测试中");
        MyQueue<Object> queue = queueFactory.getOrCreateQueue(getQueueName(task.getTaskId()));
        if (queue == null) {
            return RepCode.R_TaskCreateResultQueueFail;
        }
        threadFactory.StartIsNullOrCreate(getThreadName(task.getTaskId()), new TaskResultOptRunnable(queue, taskRefMysqlComp));

        //更新数据库
        Task update = new Task();
        update.setTaskId(task.getTaskId());
        update.setTaskState(TaskState.TESTING.ordinal());
        long time = System.currentTimeMillis();
        if (task.getTaskType() > 1) {
            time += MagicMathConstData.TASK_TO_TEST_TIME;
        }
        update.setTaskStartTime(time);
        boolean success = taskMysqlComp.updateTaskByTaskId(update);
        if (!success) {
            return RepCode.R_UpdateDbFailed;
        }
        return RepCode.R_Ok;
    }

    /**
     * 任务状态从测试中到暂停
     * 1、修改任务结束时间、持续时间、状态
     * 2、将所有正在测试该任务的用户的状态都改为暂停
     * 3、将队列中的数据消费完（消费完只的是，测试时间在结束之前的）
     * 4、关闭队列
     * 5、关闭线程
     *
     * @param task 任务
     */
    private RepCode updateTaskStateToPause(Task task, boolean fromEnd) {
        logger.info("任务状态从测试中到暂停");

        //更新数据库
        Task update = new Task();
        update.setTaskId(task.getTaskId());
        update.setTaskState(fromEnd ? TaskState.END.ordinal() : TaskState.PAUSE.ordinal());
        long time = System.currentTimeMillis();
        update.setTaskEndTime(time);
        Long taskTestTime = task.getTaskTestTime();
        update.setTaskTestTime(taskTestTime == null ? 0 : taskTestTime + task.getTaskEndTime() - time);
        boolean success = taskMysqlComp.updateTaskByTaskId(update);
        if (!success) {
            return RepCode.R_UpdateDbFailed;
        }

        //将所有正在测试该任务的用户的状态都改为暂停
        for (TaskUserRef taskUserRef : taskRefMysqlComp.selectTaskRefByTaskIdAndState(task.getTaskId(), PTaskState.TESTING)) {
            taskUserRef.setRefState(fromEnd ? PTaskState.END.ordinal() : PTaskState.PAUSE.ordinal());
            boolean suc = taskRefMysqlComp.updateTaskUserRefByRefId(taskUserRef);
            if (!suc) {
                logger.error("update DB Failed!!!");
            }
        }

        // 多让他处理十秒，十秒处理不完强制关闭
        new Thread(() -> {
            // 关闭队列
            long startTime = System.currentTimeMillis();
            while (!queueFactory.removeQueueIfEmpty(getQueueName(task.getTaskId()))) {
                // 强制关闭
                if (MagicMathConstData.TASK_MORE_CLOSE_TIME + startTime > System.currentTimeMillis()) {
                    queueFactory.removeQueue(getQueueName(task.getTaskId()));
                    break;
                }
            }

            //关闭执行线程
            threadFactory.CloseThread(getThreadName(task.getTaskId()));
        }, MagicMathConstData.TASK_TEST_RESULT_CLOSE_THREAD_PREFIX + task.getTaskId()
        ).start();
        return RepCode.R_Ok;
    }

    /**
     * 任务状态从测试中到结束
     *
     * @param task 任务
     */
    private RepCode updateTaskStateToEnd(Task task, boolean fromPause) {
        if (fromPause) {
            RepCode code = updateTaskStateToPause(task, true);
            if (code != RepCode.R_Ok) {
                return code;
            }
        }
        logger.info("任务状态从测试中到结束");
        //更新数据库
        Task update = new Task();
        update.setTaskId(task.getTaskId());
        update.setTaskState(TaskState.END.ordinal());
        boolean success = taskMysqlComp.updateTaskByTaskId(update);
        if (!success) {
            return RepCode.R_UpdateDbFailed;
        }

        // 等得消费线程十秒或者队列关闭
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (queueFactory.getQueue(getQueueName(task.getTaskId())) != null) {
                // 强制关闭
                if (MagicMathConstData.TASK_MORE_CLOSE_TIME + startTime > System.currentTimeMillis()) {
                    break;
                }
            }
            // 处理结果集
            AfterTaskEnd(task.getTaskId());
        }, MagicMathConstData.TASK_TEST_RESULT_CLOSE_THREAD_PREFIX + task.getTaskId()
        ).start();
        return RepCode.R_Ok;
    }


    /**
     * 处理任务测试结果集
     *
     * @param taskId 任务id
     */
    private void AfterTaskEnd(String taskId) {
        TaskPoJo taskPoJo = new TaskPoJo();
        taskPoJo.setTaskId(taskId);
        long allTestTime = 0;
        long allCount = 0;
        long allSucCount = 0;
        long allFailedCount = 0;
        long allCodeFailedCount = 0;
        long allTargetFailedCount = 0;
        ArrayList<TaskUserResult> list = new ArrayList<>();
        List<TaskUserRef> taskUserRefs = taskRefMysqlComp.selectTasksByTaskId(taskId);
        for (TaskUserRef ref : taskUserRefs) {
            allTestTime += ref.getRefTestTime();
            allCount += ref.getRefAllReq();
            allSucCount += ref.getRefSuccessReq();
            allFailedCount += ref.getRefFailedReq();
            allCodeFailedCount += ref.getRefFailedCode();
            allTargetFailedCount += ref.getRefFailedTarget();
            list.add(new TaskUserResult(ref));
        }

        // 修改数据库
        TaskResult result = new TaskResult(allTestTime, allCount, allSucCount, allFailedCount, allCodeFailedCount, allTargetFailedCount, list);
        taskPoJo.setTaskTestResult(result);
        Task task = new Task(taskPoJo);
        boolean b = taskMysqlComp.updateTaskByTaskId(task);
        if (!b) {
            return;
        }

        // 发钱
        for (TaskUserRef ref : taskUserRefs) {
            double v = ((float) ref.getRefAllReq() / allCount * 1.00) * task.getTaskMoney();
            User user = userMysqlComp.findUserByUserId(ref.getRefUserId());
            if (user == null) {
                continue;
            }
            User update = new User();
            update.setUserId(user.getUserId());
            update.setUserMoney(user.getUserMoney() + v);
            userMysqlComp.updateUserByUserId(user);
        }
    }


    private String getQueueName(String taskId) {
        return MagicMathConstData.TASK_TEST_RESULT_OPT_QUEUE_PREFIX + taskId;
    }

    private String getThreadName(String taskId) {
        return MagicMathConstData.TASK_RESULT_OPTION_THREAD_PREFIX + taskId;
    }
}
