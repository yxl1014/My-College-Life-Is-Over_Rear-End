package org.task.service;

import org.commons.response.ReBody;
import org.task.entity.TaskQueryRequest;

import java.util.List;

/**
 * 公共任务服务类
 * 测试者和发布者都有的接口
 * @author yxl17
 * @Package : org.task.service
 * @Create on : 2024/3/25 20:12
 **/
public interface ITaskBaseService {
    ReBody listTasks(List<String> taskIds);

    ReBody listAllTasks(TaskQueryRequest queryRequest);

    ReBody listAllTasksBetween(TaskQueryRequest queryRequest);
}
