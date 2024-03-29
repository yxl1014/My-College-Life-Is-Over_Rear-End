package org.task.service.impl;

import org.apache.logging.log4j.Logger;
import org.commons.log.LogComp;
import org.database.mysql.BaseMysqlComp;
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

    public TaskProviderServiceImpl(UserMysqlComp userMysqlComp, TaskMysqlComp taskMysqlComp, BaseMysqlComp baseMysqlComp, ITaskBaseService taskBaseService) {
        this.userMysqlComp = userMysqlComp;
        this.taskMysqlComp = taskMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
        this.taskBaseService = taskBaseService;
    }
}
