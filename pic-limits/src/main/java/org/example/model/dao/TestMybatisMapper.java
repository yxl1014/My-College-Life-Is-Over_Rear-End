package org.example.model.dao;

import org.example.model.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/20
 */
@Mapper

public interface TestMybatisMapper {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);

}
