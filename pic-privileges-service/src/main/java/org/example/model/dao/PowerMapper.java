package org.example.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.entity.Power;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Mapper
public interface PowerMapper {

    void insert (Power power);
    void  delete (Power power);
    void update (Power power);
    Power selectOne(Integer powerId);
    List<Power> selectAll();
}
