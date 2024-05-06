package org.task.service.impl;

import lombok.SneakyThrows;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.ThreadLocalComp;
import org.commons.domain.LoginCommonData;
import org.commons.log.LogComp;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.TaskUserRef;
import org.database.mysql.domain.task.*;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.service.TaskMysqlComp;
import org.database.mysql.service.TaskRefMysqlComp;
import org.database.mysql.service.UserMysqlComp;
import org.springframework.stereotype.Service;
import org.task.entity.TaskQueryRequest;
import org.task.service.ITaskBaseService;
import org.task.service.ITaskProviderService;

import java.util.List;

/**
 * @author yxl17
 * @Package : org.task.service.impl
 * @Create on : 2024/3/25 20:20
 **/
@Service
public class TaskProviderServiceImpl implements ITaskProviderService {
    private final Logger logger = LogComp.getLogger(TaskProviderServiceImpl.class);

    private final UserMysqlComp userMysqlComp;

    private final TaskMysqlComp taskMysqlComp;

    private final BaseMysqlComp baseMysqlComp;

    private final ITaskBaseService taskBaseService;

    private final ThreadLocalComp threadLocalComp;

    private final TaskRefMysqlComp taskRefMysqlComp;

    public TaskProviderServiceImpl(UserMysqlComp userMysqlComp, TaskMysqlComp taskMysqlComp, BaseMysqlComp baseMysqlComp, ITaskBaseService taskBaseService, ThreadLocalComp threadLocalComp, TaskRefMysqlComp taskRefMysqlComp) {
        this.userMysqlComp = userMysqlComp;
        this.taskMysqlComp = taskMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
        this.taskBaseService = taskBaseService;
        this.threadLocalComp = threadLocalComp;
        this.taskRefMysqlComp = taskRefMysqlComp;
    }

    @SneakyThrows
    @Override
    public ReBody activeTask(String taskId) {
        if (Strings.isEmpty(taskId)) {
            return new ReBody(RepCode.R_ParamNull);
        }
        Task task = taskMysqlComp.selectTaskByTaskId(taskId);
        if (task == null) {
            return new ReBody(RepCode.R_TaskNotFound);
        }
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        String userId = commonData.getUserId();
        // 判断任务状态 只有在开始前可以修改
        if (task.getTaskState() != TaskState.REGISTER.ordinal()) {
            return new ReBody(RepCode.R_TaskCanNotRegister);
        }
        TaskUserRef taskUserRef = taskRefMysqlComp.selectTaskByIdAndUserId(taskId, userId);
        if (taskUserRef != null) {
            return new ReBody(RepCode.R_TaskIsAction);
        }
        TaskUserRef ref = new TaskUserRef();
        ref.setRefTaskId(taskId);
        ref.setRefUserId(userId);
        MysqlBuilder<TaskUserRef> builder = new MysqlBuilder<>(TaskUserRef.class);
        builder.setCondition(ref);
        Integer ok = baseMysqlComp.insert(builder);
        if (ok != 1) {
            return new ReBody(RepCode.R_UpdateDbFailed);
        }
        return new ReBody(RepCode.R_Ok);
    }

    @Override
    public ReBody listProviderTask(TaskQueryRequest queryRequest) {
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null) {
            return new ReBody(RepCode.R_LoginTimeout);
        }
        TaskUserRef ref = new TaskUserRef();
        ref.setRefUserId(commonData.getUserId());

        TaskUserRef taskUserRef = queryRequest.getTaskUserRef();
        ref.setRefTaskId(taskUserRef.getRefTaskId());
        ref.setRefState(taskUserRef.getRefState());
        List<TaskRefPojo> taskRefPoJos = taskRefMysqlComp.selectTasks(ref, queryRequest.getPage(), queryRequest.getPageSize());
        return new ReBody(RepCode.R_Ok, taskRefPoJos);
    }


    @Override
    public ReBody updateTaskState(TaskPoJo poJo) {
        Integer taskState = poJo.getTaskState();
        String taskId = poJo.getTaskId();
        if (Strings.isEmpty(taskId) || taskState == null) {
            return new ReBody(RepCode.R_ParamNull);
        }

        Task task = taskMysqlComp.selectTaskByTaskId(taskId);
        if (task == null) {
            return new ReBody(RepCode.R_TaskNotFound);
        }

        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        String userId = commonData.getUserId();

        TaskUserRef taskUserRef = taskRefMysqlComp.selectTaskByIdAndUserId(taskId, userId);
        if (taskUserRef == null) {
            return new ReBody(RepCode.R_TaskIsNotAction);
        }

        // 如果本来就是直接返回成功
        if (taskUserRef.getRefState().equals(taskState)) {
            return new ReBody(RepCode.R_Ok);
        }
        if (taskState < 0 || taskState >= PTaskState.values().length) {
            return new ReBody(RepCode.R_ParamError);
        }

        RepCode code = updateTaskState(PTaskState.values()[taskState], task, taskUserRef);
        return new ReBody(code);
    }

    private RepCode updateTaskState(PTaskState value, Task task, TaskUserRef taskUserRef) {
        RepCode code = RepCode.R_Ok;
        switch (value) {
            case TESTING: {
                if (taskUserRef.getRefState() == PTaskState.END.ordinal()) {
                    code = RepCode.R_TaskRefIsEnd;
                    break;
                }
                if (task.getTaskState() != TaskState.TESTING.ordinal()) {
                    code = RepCode.R_TaskIsNotRunning;
                    break;
                }

                code = updateToTesting(task, taskUserRef);
                break;
            }
            case PAUSE: {
                if (taskUserRef.getRefState() != PTaskState.TESTING.ordinal()) {
                    code = RepCode.R_TaskRefNotTest;
                    break;
                }

                code = updateToPause(task, taskUserRef);
                break;
            }
            case END: {
                if (taskUserRef.getRefState() == PTaskState.TESTING.ordinal()) {
                    code = RepCode.R_TaskRefIsEnd;
                    break;
                }
                if (taskUserRef.getRefState() == TaskState.PAUSE.ordinal()) {
                    code = updateToEnd(task, taskUserRef, true);
                } else {
                    code = updateToEnd(task, taskUserRef, false);
                }
                break;
            }
            case WAITING: {
                code = RepCode.R_Error;
                break;
            }
            default: {
                break;
            }
        }
        return code;
    }


    /**
     * 测试者开始任务
     *
     * @param task        任务
     * @param taskUserRef 测试者任务
     * @return 返回
     */
    private RepCode updateToTesting(Task task, TaskUserRef taskUserRef) {
        // 改数据库
        TaskUserRef update = new TaskUserRef();
        update.setRefTaskId(taskUserRef.getRefTaskId());
        update.setRefUserId(taskUserRef.getRefUserId());
        update.setRefState(PTaskState.TESTING.ordinal());
        update.setRefStartTime(System.currentTimeMillis());
        boolean b = taskRefMysqlComp.updateTaskUserRef(update);
        if (!b) {
            return RepCode.R_UpdateDbFailed;
        }
        return RepCode.R_Ok;
    }


    /**
     * 测试者暂停任务
     *
     * @param task        任务
     * @param taskUserRef 测试者任务
     * @return 返回
     */
    private RepCode updateToPause(Task task, TaskUserRef taskUserRef) {
        //TODO YXL

        // 改数据库
        TaskUserRef update = new TaskUserRef();
        update.setRefTaskId(taskUserRef.getRefTaskId());
        update.setRefUserId(taskUserRef.getRefUserId());
        update.setRefState(PTaskState.PAUSE.ordinal());
        update.setRefEndTime(System.currentTimeMillis());
        update.setRefTestTime(taskUserRef.getRefTestTime() + update.getRefEndTime() - taskUserRef.getRefStartTime());
        // 还得统计一下呢
        boolean b = taskRefMysqlComp.updateTaskUserRef(update);
        if (!b) {
            return RepCode.R_UpdateDbFailed;
        }
        return RepCode.R_Ok;
    }

    /**
     * 测试者结束任务
     *
     * @param task        任务
     * @param taskUserRef 测试者任务
     * @return 返回
     */
    private RepCode updateToEnd(Task task, TaskUserRef taskUserRef, boolean fromPause) {
        if (fromPause) {
            RepCode code = updateToPause(task, taskUserRef);
            if (code != RepCode.R_Ok) {
                return code;
            }
        }
        //TODO 走暂停到结束的逻辑
        return RepCode.R_Ok;
    }
}
