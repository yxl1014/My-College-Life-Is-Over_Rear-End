package org.starter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commons.annotation.ControllerLog;
import org.commons.annotation.NeedCheck;
import org.commons.domain.RoleType;
import org.commons.domain.TaskTestMsg;
import org.commons.response.ReBody;
import org.database.mysql.domain.task.TaskPoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.entity.TaskQueryRequest;
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
    @PostMapping("/listTasks")
    @ControllerLog(url = "/listTasks", msg = "获取任务", roleType = RoleType.PROVIDER)
    @ApiOperation("获取任务")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody listTasks(@RequestBody List<String> taskIds) {
        return taskBaseService.listTasks(taskIds);
    }

    @PostMapping("/listAllTasks")
    @ControllerLog(url = "/listAllTasks", msg = "获取任务(模糊查询)", roleType = RoleType.PROVIDER)
    @ApiOperation("获取任务(模糊查询)")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody listAllTasks(@RequestBody TaskQueryRequest queryRequest) {
        return taskBaseService.listAllTasks(queryRequest);
    }

    @PostMapping("/listAllTasksBetween")
    @ControllerLog(url = "/listAllTasksBetween", msg = "获取任务(区间查询)", roleType = RoleType.PROVIDER)
    @ApiOperation("获取任务(区间查询)")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    public ReBody listAllTasksBetween(@RequestBody TaskQueryRequest queryRequest) {
        return taskBaseService.listAllTasksBetween(queryRequest);
    }


    // =================================通=用=================================

    // ===============================任务测试者===============================
    @PostMapping("/activeTask")
    @ControllerLog(url = "/activeTask", msg = "接受任务", roleType = RoleType.PROVIDER)
    @ApiOperation("接受任务")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody activeTask(@RequestBody TaskPoJo poJo) {
        return taskProviderService.activeTask(poJo.getTaskId());
    }

    @PostMapping("listProviderTask")
    @ControllerLog(url = "/listProviderTask", msg = "获取任务测试者接受的任务", roleType = RoleType.PROVIDER)
    @ApiOperation("获取任务测试者接受的任务")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody listProviderTask(@RequestBody TaskQueryRequest queryRequest) {
        return taskProviderService.listProviderTask(queryRequest);
    }


    @PostMapping("p_updateTaskState")
    @ControllerLog(url = "/p_updateTaskState", msg = "测试者修改任务状态", roleType = RoleType.PROVIDER)
    @ApiOperation("测试者修改任务状态")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody p_updateTaskState(@RequestBody TaskPoJo poJo) {
        return taskProviderService.updateTaskState(poJo);
    }


    @PostMapping("pushTaskResult")
    @ControllerLog(url = "/pushTaskResult", msg = "推送测试结果数据", roleType = RoleType.PROVIDER)
    @ApiOperation("推送测试结果数据")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody pushTaskResult(@RequestBody List<TaskTestMsg> msgs) {
        return taskProviderService.pushTaskResult(msgs);
    }

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

    @PostMapping("listConsumerTask")
    @ControllerLog(url = "/listConsumerTask", msg = "获取任务发布者的任务", roleType = RoleType.CONSUMER)
    @ApiOperation("获取任务发布者的任务")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody listConsumerTask(@RequestBody TaskQueryRequest queryRequest) {
        return taskConsumerService.listConsumerTask(queryRequest);
    }

    @PostMapping("c_updateTaskState")
    @ControllerLog(url = "/c_updateTaskState", msg = "发布者修改任务状态", roleType = RoleType.CONSUMER)
    @ApiOperation("发布者修改任务状态")
    @ApiResponse(code = 200, message = "成功", response = ReBody.class)
    @NeedCheck
    public ReBody updateTaskState(@RequestBody TaskPoJo poJo) {
        return taskConsumerService.updateTaskState(poJo);
    }


    // ===============================任务发布者===============================
}
