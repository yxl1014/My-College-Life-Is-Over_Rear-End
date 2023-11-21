package org.example.service.Impl;

import org.example.model.dao.TestMybatisMapper;
import org.example.model.entity.TestMybatis;
import org.example.service.TestMybatisService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/20
 */
@Service("testMybatisService")
public class TestMybatisServiceImpl implements TestMybatisService {
    @Resource
    private TestMybatisMapper testMybatisMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TestMybatis queryById(Integer id) {
        return this.testMybatisMapper.queryById(id);
    }

}

