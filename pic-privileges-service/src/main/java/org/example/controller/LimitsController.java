package org.example.controller;

import io.swagger.annotations.ApiOperation;
import org.example.model.dao.LimitsMapper;
import org.example.model.entity.Power;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@RestController
public class LimitsController {
    //测试sheen
    @GetMapping("/sheen")
    @ApiOperation("测试联通")
    public String get(){return "hello sheen!";}
@Resource
private LimitsMapper limitsMapper;
    @GetMapping("/getOne")
    @ApiOperation("测试查询数据库")
    public Power selectOne(){
        return this.limitsMapper.selectOne(1);
    }
}
