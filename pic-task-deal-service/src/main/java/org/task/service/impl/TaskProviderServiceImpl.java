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
import org.database.mysql.domain.task.Task;
import org.database.mysql.domain.task.TaskState;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.service.TaskMysqlComp;
import org.database.mysql.service.UserMysqlComp;
import org.springframework.stereotype.Service;
import org.task.service.ITaskBaseService;
import org.task.service.ITaskProviderService;

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

    public TaskProviderServiceImpl(UserMysqlComp userMysqlComp, TaskMysqlComp taskMysqlComp, BaseMysqlComp baseMysqlComp, ITaskBaseService taskBaseService, ThreadLocalComp threadLocalComp) {
        this.userMysqlComp = userMysqlComp;
        this.taskMysqlComp = taskMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
        this.taskBaseService = taskBaseService;
        this.threadLocalComp = threadLocalComp;
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
}
