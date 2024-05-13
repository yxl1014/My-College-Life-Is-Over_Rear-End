package org.database.mysql.service;

import lombok.SneakyThrows;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.TaskUserRef;
import org.database.mysql.domain.task.PTaskState;
import org.database.mysql.domain.task.TaskPoJo;
import org.database.mysql.domain.task.TaskRefPojo;
import org.database.mysql.entity.ConditionType;
import org.database.mysql.entity.MysqlBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Package : org.database.mysql.service
 * @Create on : 2024/4/22 14:37
 **/

@Component
public class TaskRefMysqlComp {
    private final TaskMysqlComp taskMysqlComp;

    private final BaseMysqlComp baseMysqlComp;

    public TaskRefMysqlComp(BaseMysqlComp baseMysqlComp, TaskMysqlComp taskMysqlComp) {
        this.baseMysqlComp = baseMysqlComp;
        this.taskMysqlComp = taskMysqlComp;
    }

    @SneakyThrows
    public List<TaskRefPojo> selectTasks(TaskUserRef in, int offset, int limit) {
        MysqlBuilder<TaskUserRef> taskMysqlBuilder = new MysqlBuilder<>(TaskUserRef.class);
        taskMysqlBuilder.setCondition(in);
        taskMysqlBuilder.setQueryType(ConditionType.LIKE);
        List<TaskUserRef> taskUserRefs = baseMysqlComp.selectPage(taskMysqlBuilder, offset, limit);
        List<String> taskIds = new ArrayList<>();
        for (TaskUserRef taskUserRef : taskUserRefs) {
            taskIds.add(taskUserRef.getRefTaskId());
        }

        List<TaskRefPojo> taskRefPoJos = new ArrayList<>();

        List<TaskPoJo> taskPoJos = taskMysqlComp.selectTasksByTaskIds(taskIds);

        for (TaskPoJo taskPoJo : taskPoJos) {
            for (TaskUserRef ref : taskUserRefs) {
                if (ref.getRefTaskId().equals(taskPoJo.getTaskId())) {
                    taskRefPoJos.add(new TaskRefPojo(ref, taskPoJo));
                }
            }
        }

        return taskRefPoJos;
    }


    @SneakyThrows
    public TaskUserRef selectTaskByIdAndUserId(String taskId, String userId) {
        MysqlBuilder<TaskUserRef> taskMysqlBuilder = new MysqlBuilder<>(TaskUserRef.class);
        TaskUserRef ref = new TaskUserRef();
        ref.setRefTaskId(taskId);
        ref.setRefUserId(userId);
        taskMysqlBuilder.setCondition(ref);
        return baseMysqlComp.selectOne(taskMysqlBuilder);
    }

    @SneakyThrows
    public boolean updateTaskUserRefByTaskIdAndUerId(TaskUserRef taskUserRef) {
        MysqlBuilder<TaskUserRef> taskMysqlBuilder = new MysqlBuilder<>(TaskUserRef.class);
        TaskUserRef in = new TaskUserRef();
        in.setRefTaskId(taskUserRef.getRefTaskId());
        in.setRefUserId(taskUserRef.getRefUserId());
        taskMysqlBuilder.setCondition(in);
        taskMysqlBuilder.setUpdate(taskUserRef);
        return baseMysqlComp.update(taskMysqlBuilder) == 1;
    }

    @SneakyThrows
    public boolean updateTaskUserRefByRefId(TaskUserRef taskUserRef) {
        MysqlBuilder<TaskUserRef> taskMysqlBuilder = new MysqlBuilder<>(TaskUserRef.class);
        TaskUserRef in = new TaskUserRef();
        in.setRefId(taskUserRef.getRefId());
        taskMysqlBuilder.setCondition(in);
        taskMysqlBuilder.setUpdate(taskUserRef);
        return baseMysqlComp.update(taskMysqlBuilder) == 1;
    }

    @SneakyThrows
    public List<TaskUserRef> selectTaskRefByTaskIdAndState(String taskId, PTaskState state) {
        MysqlBuilder<TaskUserRef> taskMysqlBuilder = new MysqlBuilder<>(TaskUserRef.class);
        TaskUserRef ref = new TaskUserRef();
        ref.setRefTaskId(taskId);
        ref.setRefState(state.ordinal());
        taskMysqlBuilder.setCondition(ref);

        TaskUserRef out = new TaskUserRef();
        out.setRefId(1);
        out.setRefState(1);
        return baseMysqlComp.selectList(taskMysqlBuilder);
    }
}
