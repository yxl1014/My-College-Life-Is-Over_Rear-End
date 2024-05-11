package org.async.runnable;

import org.apache.logging.log4j.Logger;
import org.commons.domain.TaskTestMsg;
import org.commons.log.LogComp;
import org.database.mysql.domain.TaskUserRef;
import org.database.mysql.service.TaskRefMysqlComp;
import org.mq.MyQueue;

import java.util.List;

/**
 * 任务结果处理线程
 *
 * @author Administrator
 * @Package : org.async.runnable
 * @Create on : 2024/5/11 下午7:13
 **/

public class TaskResultOptRunnable implements Runnable {
    private final Logger log = LogComp.getLogger(TaskResultOptRunnable.class);

    private final MyQueue<Object> queue;
    private final TaskRefMysqlComp taskRefMysqlComp;

    public TaskResultOptRunnable(MyQueue<Object> queue, TaskRefMysqlComp taskRefMysqlComp) {
        this.queue = queue;
        this.taskRefMysqlComp = taskRefMysqlComp;
    }

    @Override
    public void run() {
        log.info("thread:{} start suc", Thread.currentThread().getName());
        while (true) {
            if (queue == null) {
                break;
            }
            List<TaskTestMsg> pop = (List<TaskTestMsg>) queue.pop();
            if (pop == null || pop.isEmpty()) {
                continue;
            }

            TaskTestMsg taskTestMsg = pop.get(0);

            TaskUserRef taskUserRef = taskRefMysqlComp.selectTaskByIdAndUserId(taskTestMsg.getTaskId(), taskTestMsg.getUserId());
            if (taskUserRef == null) {
                return;
            }
            TaskUserRef update = getTaskUserRef(taskUserRef, pop);

            boolean b = taskRefMysqlComp.updateTaskUserRefByRefId(update);
            if (!b) {
                log.warn("update task user ref failed");
            }
            log.info("option one result push!");
        }
    }

    private static TaskUserRef getTaskUserRef(TaskUserRef taskUserRef, List<TaskTestMsg> pop) {
        TaskUserRef update = new TaskUserRef();
        update.setRefId(taskUserRef.getRefId());
        Long all = taskUserRef.getRefAllReq();
        Long suc = taskUserRef.getRefSuccessReq();
        Long fail = taskUserRef.getRefFailedReq();
        Long failCode = taskUserRef.getRefFailedCode();
        Long failTarget = taskUserRef.getRefFailedTarget();

        for (TaskTestMsg msg : pop) {
            all++;
            switch (msg.getTestState()) {
                case 0:
                    suc++;
                    break;
                case 1:
                    fail++;
                    failCode++;
                    break;
                case 2:
                    fail++;
                    failTarget++;
                    break;
                default:
                    continue;
            }
        }
        update.setRefAllReq(all);
        update.setRefSuccessReq(suc);
        update.setRefFailedReq(fail);
        update.setRefFailedCode(failCode);
        update.setRefFailedTarget(failTarget);
        return update;
    }
}
