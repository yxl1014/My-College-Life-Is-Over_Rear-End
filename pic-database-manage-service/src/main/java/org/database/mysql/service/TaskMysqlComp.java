package org.database.mysql.service;

import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.task.Task;
import org.database.mysql.domain.task.TaskPoJo;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Package : org.database.mysql.service
 * @Create on : 2024/3/29 下午2:03
 **/


@Component
public class TaskMysqlComp {
    private final BaseMysqlComp baseMysqlComp;

    private final TaskMapper taskMapper;

    public TaskMysqlComp(BaseMysqlComp baseMysqlComp, TaskMapper taskMapper) {
        this.baseMysqlComp = baseMysqlComp;
        this.taskMapper = taskMapper;
    }

    @SneakyThrows
    public List<TaskPoJo> selectTasksByTaskIds(List<String> taskIds) {
        List<TaskPoJo> result = new ArrayList<>();
        if (taskIds == null || taskIds.isEmpty()) {
            return result;
        }
        for (String taskId : taskIds) {
            Task temp = selectTaskByTaskId(taskId);
            if (temp == null) {
                continue;
            }
            result.add(new TaskPoJo(temp));
        }
        return result;
    }

    @SneakyThrows
    public Task selectTaskByTaskId(String taskId) {
        Task in = new Task();
        MysqlBuilder<Task> taskMysqlBuilder = new MysqlBuilder<>(Task.class);
        in.setTaskId(taskId);
        taskMysqlBuilder.setIn(in);
        return baseMysqlComp.selectOne(taskMysqlBuilder);
    }

    @SneakyThrows
    public boolean updateTaskByTaskId(Task task) {
        if (Strings.isEmpty(task.getTaskId())){
            return false;
        }
        MysqlBuilder<Task> mysqlBuilder = new MysqlBuilder<>(Task.class);
        mysqlBuilder.setIn(new Task(task.getTaskId()));
        mysqlBuilder.setUpdate(task);
        Integer update = baseMysqlComp.update(mysqlBuilder);
        return update == 1;
    }
}
