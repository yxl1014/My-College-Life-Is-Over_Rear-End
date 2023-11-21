package org.example.model.dao;

import org.example.model.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/20
 */
@Mapper
public interface TestMybatisMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public TestMybatis queryById(@Param("id") Integer id);

}
