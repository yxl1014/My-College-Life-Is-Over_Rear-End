package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commons.annotation.ControllerLog;
import org.commons.annotation.NeedCheck;
import org.commons.domain.RoleType;
import org.commons.response.ReBody;
import org.database.mysql.domain.task.TaskPoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.task.service.ITaskBaseService;
import org.task.service.ITaskConsumerService;
import org.task.service.ITaskProviderService;

import java.util.List;

/**
 * @author Administrator
 * @Package : org.starter.controller
 * @Create on : 2024/3/29 上午11:03
 **/

@RestController
@Api(tags = "任务接口")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskProviderService taskProviderService;

    @Autowired
    private ITaskConsumerService taskConsumerService;

    @Autowired
    private ITaskBaseService taskBaseService;


    // =================================通=用=================================
    @GetMapping("/listTasks")
    @ControllerLog(url = "/listTasks", msg = "获取任务", roleType = RoleType.PROVIDER)
    @ApiOperation("获取任务")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody listTasks(@RequestBody List<String> taskIds) {
        return taskBaseService.listTasks(taskIds);
    }
    // =================================通=用=================================

    // ===============================任务测试者===============================
    // ===============================任务测试者===============================

    // ===============================任务发布者===============================
    @PostMapping("/createTask")
    @ControllerLog(url = "/createTask", msg = "发布任务", roleType = RoleType.CONSUMER)
    @ApiOperation("发布任务")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody createTask(@RequestBody TaskPoJo poJo) {
        return taskConsumerService.createTask(poJo);
    }

    @PostMapping("/updateTaskShell")
    @ControllerLog(url = "/updateTaskShell", msg = "修改任务脚本", roleType = RoleType.CONSUMER)
    @ApiOperation("修改任务脚本")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody updateTaskShell(@RequestBody TaskPoJo poJo) {
        return taskConsumerService.updateTaskShell(poJo);
    }
    // ===============================任务发布者===============================
}
