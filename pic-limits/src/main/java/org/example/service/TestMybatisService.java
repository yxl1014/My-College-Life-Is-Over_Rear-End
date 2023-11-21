package org.example.service;

import org.example.model.entity.TestMybatis;
import org.example.model.dao.TestMybatisMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/20
 */

public interface TestMybatisService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);


}

