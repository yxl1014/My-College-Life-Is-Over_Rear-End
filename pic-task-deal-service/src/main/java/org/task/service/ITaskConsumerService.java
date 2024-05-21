package org.task.service;

import org.commons.response.ReBody;
import org.database.mysql.domain.task.TaskPoJo;
import org.task.entity.TaskQueryRequest;

import java.util.List;

/**
 * 发布者任务服务类
 * @author yxl17
 * @Package : org.task.service
 * @Create on : 2024/3/25 20:11
 **/
public interface ITaskConsumerService{
    ReBody createTask(TaskPoJo taskPoJo);

    ReBody updateTaskShell(TaskPoJo taskPoJo);

    ReBody listTasks(List<String> taskIds);

    ReBody listConsumerTask(TaskQueryRequest queryRequest);

    ReBody updateTaskState(TaskPoJo poJo);

    void onServerClose();
}
