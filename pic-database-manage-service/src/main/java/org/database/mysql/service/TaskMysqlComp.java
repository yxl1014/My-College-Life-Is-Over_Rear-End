package org.database.mysql.service;

import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.task.Task;
import org.database.mysql.domain.task.TaskPoJo;
import org.database.mysql.entity.ConditionType;
import org.database.mysql.entity.MysqlBuilder;
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

    public TaskMysqlComp(BaseMysqlComp baseMysqlComp) {
        this.baseMysqlComp = baseMysqlComp;
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
        taskMysqlBuilder.setCondition(in);
        return baseMysqlComp.selectOne(taskMysqlBuilder);
    }

    @SneakyThrows
    public List<Task> selectTasks(Task in, ConditionType conditionType, int offset, int limit) {
        MysqlBuilder<Task> taskMysqlBuilder = new MysqlBuilder<>(Task.class);
        taskMysqlBuilder.setCondition(in);
        taskMysqlBuilder.setQueryType(conditionType);
        return baseMysqlComp.selectPage(taskMysqlBuilder, offset, limit);
    }

    @SneakyThrows
    public List<Task> selectTasks(Task in, int offset, int limit) {
        return selectTasks(in, ConditionType.EQ, offset, limit);
    }

    @SneakyThrows
    public List<Task> selectTasks(Task in, ConditionType conditionType) {
        MysqlBuilder<Task> taskMysqlBuilder = new MysqlBuilder<>(Task.class);
        taskMysqlBuilder.setCondition(in);
        taskMysqlBuilder.setQueryType(conditionType);
        return baseMysqlComp.selectList(taskMysqlBuilder);
    }

    @SneakyThrows
    public List<Task> selectTasks(Task in) {
        return selectTasks(in, ConditionType.EQ);
    }

    @SneakyThrows
    public boolean updateTaskByTaskId(Task task) {
        if (Strings.isEmpty(task.getTaskId())) {
            return false;
        }
        MysqlBuilder<Task> mysqlBuilder = new MysqlBuilder<>(Task.class);
        mysqlBuilder.setCondition(new Task(task.getTaskId()));
        mysqlBuilder.setUpdate(task);
        Integer update = baseMysqlComp.update(mysqlBuilder);
        return update == 1;
    }

    public List<TaskPoJo> taskToPojo(List<Task> tasks){
        List<TaskPoJo> poJoList = new ArrayList<>();
        if (tasks == null){
            return poJoList;
        }
        for (Task task:tasks){
            poJoList.add(new TaskPoJo(task));
        }
        return poJoList;
    }
}
