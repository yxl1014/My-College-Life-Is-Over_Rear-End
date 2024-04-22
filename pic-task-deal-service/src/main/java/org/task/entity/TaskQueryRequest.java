package org.task.entity;

/**
 * @author Administrator
 * @Package : org.user.entity.request
 * @Create on : 2024/4/2 下午4:22
 **/

import lombok.Data;
import org.database.mysql.domain.TaskUserRef;
import org.database.mysql.domain.task.TaskPoJo;


@Data
public class TaskQueryRequest {
    private Integer page = 1;
    private Integer pageSize = 10;
    private TaskPoJo taskPoJo;
    private TaskUserRef taskUserRef;
}
