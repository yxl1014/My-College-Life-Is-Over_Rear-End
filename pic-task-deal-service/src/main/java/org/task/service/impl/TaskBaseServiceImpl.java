package org.task.service.impl;

import org.apache.logging.log4j.Logger;
import org.commons.log.LogComp;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.task.TaskPoJo;
import org.database.mysql.service.TaskMysqlComp;
import org.springframework.stereotype.Service;
import org.task.service.ITaskBaseService;

import java.util.List;

/**
 * @author yxl17
 * @Package : org.task.service.impl
 * @Create on : 2024/3/25 20:14
 **/

@Service
public class TaskBaseServiceImpl implements ITaskBaseService {

    private final Logger logger = LogComp.getLogger(TaskBaseServiceImpl.class);

    private final TaskMysqlComp taskMysqlComp;
    private final BaseMysqlComp baseMysqlComp;

    public TaskBaseServiceImpl(TaskMysqlComp taskMysqlComp, BaseMysqlComp baseMysqlComp) {
        this.taskMysqlComp = taskMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
    }

    @Override
    public ReBody listTasks(List<String> taskIds) {
        // 如果没有就获得全部
        if (taskIds == null || taskIds.isEmpty()) {
            return new ReBody(RepCode.R_ParamNull);
        }
        List<TaskPoJo> result = taskMysqlComp.selectTasksByTaskIds(taskIds);
        return new ReBody(RepCode.R_Ok, result);
    }
}
