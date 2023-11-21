package org.example.controller;

import org.example.model.entity.TestMybatis;
import org.example.service.TestMybatisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/21
 */
@RestController
@RequestMapping("testMybatis")
public class TestMybatisController {
    /**
     * 服务对象
     */
    @Resource
    private TestMybatisService testMybatisService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @RequestMapping("/selectOne")
    public TestMybatis selectOne(Integer id) {
        return this.testMybatisService.queryById(1);
    }


}
