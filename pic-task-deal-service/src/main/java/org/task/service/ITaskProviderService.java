package org.task.service;

import org.commons.domain.TaskTestMsg;
import org.commons.response.ReBody;
import org.database.mysql.domain.task.TaskPoJo;
import org.task.entity.TaskQueryRequest;

import java.util.List;

/**
 * 测试者任务服务类
 *
 * @author yxl17
 * @Package : org.task.service
 * @Create on : 2024/3/25 20:11
 **/
public interface ITaskProviderService {


    /**
     * 接受任务
     *
     * @param taskId 任务ID
     * @return 返回
     */
    ReBody activeTask(String taskId);

    /**
     * 获取任务测试者接受的任务
     * @param queryRequest 条件
     * @return 返回
     */
    ReBody listProviderTask(TaskQueryRequest queryRequest);



    ReBody updateTaskState(TaskPoJo poJo);

    ReBody pushTaskResult(List<TaskTestMsg> msgs);
}
